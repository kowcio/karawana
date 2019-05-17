package karawana.service;


import karawana.entities.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class RabbitListenerConsumer {


    @Autowired
    private AmqpAdmin amqpAdmin;

    private static Logger log = LoggerFactory.getLogger(RabbitListenerConsumer.class);

//    @Scheduled(fixedRate = 5000)
    @RabbitListener(queues = "NYSE")
    public void receiveMessage(final Message customMessage) {
        Properties queueProperties = amqpAdmin.getQueueProperties("NYSE");
        log.info("Received message as specific : {}\n QUEUE_MESSAGE_COUNT={}, QUEUE_CONSUMER_COUNT={}"
                , customMessage.toString(),
                queueProperties.get("QUEUE_MESSAGE_COUNT"),
                queueProperties.get("QUEUE_CONSUMER_COUNT"));

    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "myQueue", durable = "true"),
            exchange = @Exchange(value = "auto.exch", ignoreDeclarationExceptions = "true"),
            key = "orderRoutingKey")
    )
    public void processOrder(Group order) {

        //create one queue to save everything to the database as a log for users and etc
        // always add to every fanout group as we can attach admin to it

    }

}
