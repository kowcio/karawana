package allinone.service;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@ConditionalOnProperty("allinone.service.WebSocketBroadcastService.isOn")
@EnableScheduling
public class WebSocketBroadcastService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketBroadcastService.class);
    
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//    //group ID dynamic
//    @SendTo("/topic/greetings")
////    @Scheduled(fixedRate=3000)
//    public void broadcastTest() {
//    	LOGGER.info("Broadcasting messages to users on /test.");
//        messagingTemplate.convertAndSend("/topic/greetings",
//                "Broadcasting payload data." + LocalTime.now()+" Static data broadcast."	);
//    }

//    @SendTo("/topic/greetings/{groupID}")
////    @Scheduled(fixedRate=3000)
//    public void broadcastDynamicTest(
//            @DestinationVariable String groupID
//    ) {
//        LOGGER.info("Broadcasting messages to users on /test.");
//        messagingTemplate.convertAndSend("/topic/greetings/{groupID}",
//                "Broadcasting payload data." + LocalTime.now()+" " +
//                        "Got dynamic group data."	);
//    }

  
}
