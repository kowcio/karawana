package karawana.service;


import karawana.web.controller.AmqpReactiveController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class RabbitTestConsumer {


    @Autowired
    private AmqpAdmin amqpAdmin;

    private static Logger log = LoggerFactory.getLogger(RabbitTestConsumer.class);

//    @Scheduled(fixedRate = 5000)
    @RabbitListener(queues = "NYSE")
    public void receiveMessage(final Message customMessage) {
        Properties queueProperties = amqpAdmin.getQueueProperties("NYSE");
        log.info("Received message as specific : {}\n QUEUE_MESSAGE_COUNT={}, QUEUE_CONSUMER_COUNT={}"
                , customMessage.toString(),
                queueProperties.get("QUEUE_MESSAGE_COUNT"),
                queueProperties.get("QUEUE_CONSUMER_COUNT"));

    }

}
