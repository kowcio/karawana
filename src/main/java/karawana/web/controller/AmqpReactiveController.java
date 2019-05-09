package karawana.web.controller;

import karawana.config.DestinationsConfig;
import karawana.config.DestinationsConfig.DestinationInfo;
import karawana.config.MessageListenerContainerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;

@RestController
public class AmqpReactiveController {

    private static Logger log = LoggerFactory.getLogger(AmqpReactiveController.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private DestinationsConfig destinationsConfig;

    @Autowired
    private MessageListenerContainerFactory messageListenerContainerFactory;

    @PostConstruct
    public void setupQueueDestinations() {

        log.info("[I48] Creating Destinations...");


        Map<String, DestinationInfo> queues = destinationsConfig.getQueues();
        queues
                .forEach((key, destination) -> {

                    log.info("[I54] Creating directExchange: key={}, name={}, routingKey={}", key, destination.getExchange(), destination.getRoutingKey());

                    Exchange ex = ExchangeBuilder.fanoutExchange(destination.getExchange())
                            .durable(true)
                            .build();

                    amqpAdmin.declareExchange(ex);

                    Queue q = QueueBuilder.durable(destination.getRoutingKey())
                            .build();

                    amqpAdmin.declareQueue(q);

                    Binding b = BindingBuilder.bind(q)
                            .to(ex)
                            .with(destination.getRoutingKey())
                            .noargs();

                    amqpAdmin.declareBinding(b);

                    log.info("[I70] Binding successfully created.");

                });
    }

    @PostConstruct
    public void setupTopicDestinations() {

        // For topic each consumer will have its own Queue, so no binding
        destinationsConfig.getTopics()
                .forEach((key, destination) -> {

                    log.info("[I98] Creating TopicExchange: name={}, exchange={}", key, destination.getExchange());

                    Exchange ex = ExchangeBuilder.topicExchange(destination.getExchange())
                            .durable(true)
                            .build();

                    amqpAdmin.declareExchange(ex);

                    log.info("[I107] Topic Exchange successfully created.");

                });
    }

    @PostMapping(value = "/queue/{name}")
    public Mono<ResponseEntity<?>> sendMessageToQueue(@PathVariable String name, @RequestBody String payload) {
        // Lookup exchange details
        final DestinationInfo d = destinationsConfig.getQueues()
                .get(name);

        if (d == null) {
            // Destination not found.
            return Mono.just(ResponseEntity.notFound()
                    .build());
        }

        return Mono.fromCallable(() -> {
            Properties queueProperties = amqpAdmin.getQueueProperties(name);
            log.info("[I51] sendMessageToQueue: queue={}, routingKey={}, " +
                            "QUEUE_MESSAGE_COUNT={}, QUEUE_CONSUMER_COUNT={}",
                    d.getExchange(),
                    d.getRoutingKey(),
                    queueProperties.get("QUEUE_MESSAGE_COUNT"),
                    queueProperties.get("QUEUE_CONSUMER_COUNT"));

            amqpTemplate.convertAndSend(d.getExchange(), d.getRoutingKey(), payload);
            return ResponseEntity.accepted()
                    .build();

        });

    }

    /**
     * Receive messages for the given queue
     *
     * @param name
     * @param errorHandler
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/queue/{name}")//, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<?> receiveMessagesFromQueue(@PathVariable String name) {

        DestinationInfo d = destinationsConfig.getQueues()
                .get(name);

        if (d == null) {
            return Flux.just(ResponseEntity.notFound()
                    .build());
        }

        MessageListenerContainer mlc = messageListenerContainerFactory.createMessageListenerContainer(d.getRoutingKey());

        Flux<String> f = Flux.<String>create(emitter -> {

            log.info("[I168] Adding listener, queue={}", d.getRoutingKey());
            mlc.setupMessageListener((MessageListener) m -> {

                String qname = m.getMessageProperties()
                        .getConsumerQueue();

                log.info("[I137] Message received, queue={}", qname);

                if (emitter.isCancelled()) {
                    log.info("[I166] cancelled, queue={}", qname);
                    mlc.stop();
                    return;
                }

                String payload = new String(m.getBody());
                emitter.next(payload);

                log.info("[I176] Message sent to client, queue={}", qname);

            });

            emitter.onRequest(v -> {
                log.info("[I171] Starting container, queue={}", d.getRoutingKey());
                mlc.start();
            });

            emitter.onDispose(() -> {
                log.info("[I176] onDispose: queue={}", d.getRoutingKey());
                mlc.stop();
            });

            log.info("[I171] Container started, queue={}", d.getRoutingKey());

        });


        return Flux.interval(Duration.ofSeconds(5))
                .map(v -> {
                    log.info("[I209] sending keepalive message...");
                    return "No news is good news";
                })
                .mergeWith(f);
    }

    /**
     * send message to a given topic
     *
     * @param name
     * @param payload
     * @return
     */
    @PostMapping(value = "/topic/{name}")
    public Mono<ResponseEntity<?>> sendMessageToTopic(@PathVariable String name, @RequestBody String payload
            , WebSession sessionIsOK) {

        // Lookup exchange details
        final DestinationInfo d = destinationsConfig.getTopics()
                .get(name);
        if (d == null) {
            // Destination not found.
            return Mono.just(ResponseEntity.notFound()
                    .build());
        }

        return Mono.fromCallable(() -> {

            log.info("[I51] sendMessageToTopic: topic={}, routingKey={}", d.getExchange(), d.getRoutingKey());
            amqpTemplate.convertAndSend(d.getExchange(), d.getRoutingKey(), payload);

            return ResponseEntity.accepted()
                    .build();

        });
    }

    @ResponseBody
    @GetMapping(value = "/topic/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<?> receiveMessagesFromTopic(@PathVariable String name) {

        DestinationInfo d = destinationsConfig.getTopics()
                .get(name);

        if (d == null) {
            return Flux.just(ResponseEntity.notFound()
                    .build());
        }

        Queue topicQueue = createTopicQueue(d);
        String qname = topicQueue.getName();

        MessageListenerContainer mlc = messageListenerContainerFactory.createMessageListenerContainer(qname);

        Flux<String> f = Flux.<String>create(emitter -> {

            log.info("[I168] Adding listener, queue={}", qname);

            mlc.setupMessageListener((MessageListener) m -> {

                log.info("[I137] Message received, queue={}", qname);

                if (emitter.isCancelled()) {
                    log.info("[I166] cancelled, queue={}", qname);
                    mlc.stop();
                    return;
                }

                String payload = new String(m.getBody());
                emitter.next(payload);

                log.info("[I176] Message sent to client, queue={}", qname);

            });

            emitter.onRequest(v -> {
                log.info("[I171] Starting container, queue={}", qname);
                mlc.start();
            });

            emitter.onDispose(() -> {
                log.info("[I176] onDispose: queue={}", qname);
                amqpAdmin.deleteQueue(qname);
                mlc.stop();
            });

            log.info("[I171] Container started, queue={}", qname);

        });

        return Flux.interval(Duration.ofSeconds(5))
                .map(v -> {
                    log.info("[I209] sending keepalive message...");
                    return "No news is good news";
                })
                .mergeWith(f);

    }

    private Queue createTopicQueue(DestinationInfo destination) {

        Exchange ex = ExchangeBuilder.topicExchange(destination.getExchange())
                .durable(true)
                .build();

        amqpAdmin.declareExchange(ex);

        Queue q = QueueBuilder.nonDurable()
                .build();

        amqpAdmin.declareQueue(q);

        Binding b = BindingBuilder.bind(q)
                .to(ex)
                .with(destination.getRoutingKey())
                .noargs();

        amqpAdmin.declareBinding(b);

        return q;
    }

}

