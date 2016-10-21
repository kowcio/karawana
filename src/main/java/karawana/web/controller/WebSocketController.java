package karawana.web.controller;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import karawana.model.Greeting;
import karawana.model.HelloMessage;

@Controller
public class WebSocketController {

    private final Logger log = LoggerFactory.getLogger(WebSocketController.class);

    @Cacheable("myCache")
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) {
        // simulated delay

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("# Received a websocket msg = {}", message.toString());

        return new Greeting("Hello, " + message.getName() + "! - " + LocalTime.now());
    }

    // TODO - TURN ON OR OFF broadcasting under a button - API

}
//