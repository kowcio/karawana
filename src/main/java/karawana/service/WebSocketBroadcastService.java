package karawana.service;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@ConditionalOnProperty("allinone.service.WebSocketBroadcastService.isOn")
public class WebSocketBroadcastService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketBroadcastService.class);
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedRate=1000)
    public void broadcastTest() {
    	LOGGER.info("Broadcasting messages to users on /test.");
        messagingTemplate.convertAndSend("/test", "Broadcasting payload data." + LocalTime.now()	);
    }

  
}
