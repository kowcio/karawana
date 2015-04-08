package allinone.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import allinone.model.Greeting;
import allinone.model.HelloMessage;

@Controller
public class WebSocketController {
    
    private final Logger log = LoggerFactory.getLogger(WebSocketController.class);
    
    @MessageMapping("/hello/info")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        log.info("Received a websocket msg.");
        return new Greeting("Hello, " + message.getName() + "! - ");
    }
}
