package allinone.service;

import static org.fest.assertions.Assertions.assertThat;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketService.class);
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private static boolean isBroadCastTestON = true;

    @Scheduled(fixedRate=1000)
    public void broadcastTest() {
    	LOGGER.info("Broadcasting messages to users on /test.");
    	if (isBroadCastTestON)
        messagingTemplate.convertAndSend("/test", "Broadcasting payload data." + LocalTime.now()	);
    }

  
}
