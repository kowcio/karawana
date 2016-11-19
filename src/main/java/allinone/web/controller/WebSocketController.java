package allinone.web.controller;

import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebSocketController {

    private final Logger log = LoggerFactory.getLogger(WebSocketController.class);

//    @Cacheable("myCache")
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting() {
        // simulated delay

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("# Received a websocket msg .");
        log.info("Sending groupID to /topic/greetings ? ");
        return "Strin from controller";
    }

    // TODO - TURN ON OR OFF broadcasting under a button - API

}
//
