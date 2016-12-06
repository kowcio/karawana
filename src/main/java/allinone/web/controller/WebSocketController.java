package allinone.web.controller;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebSocketController {

    private final Logger log = LoggerFactory.getLogger(WebSocketController.class);

    private TaskScheduler scheduler = new ConcurrentTaskScheduler();
    @Autowired
    private SimpMessagingTemplate template;
    @Cacheable("myCache")
    @MessageMapping("/hello/{groupID}")
    @SendTo("/topic/greetings")
    public String createWebSocketServiceWithVar(@PathVariable String groupID) {
        // simulated delay
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("We want to create a websocket with :  {} -- {}", groupID, DateTime.now());
        //config service;
        Thread.currentThread().setName("ThreadCreatingSocket");
        scheduler.scheduleAtFixedRate(() -> {
            try{

                Thread.currentThread().setName("websocket:"+groupID);
                String destination = "/topic/greetings/" + groupID;
                log.info("Log from thread={}",destination);



                template.convertAndSend(destination, "{shares:true,price:100.00}");
            }catch(MessagingException e){
                System.err.println("!!!!!! websocket timer error :>"+e.toString());
            }
        }, 3000);


        System.out.println(DateTime.now());

        return "Strin from controller";
    }


    @SubscribeMapping("/chat")
    public List getChatInit() {
        log.info("Subscribe mapping method is runned.");
        return new ArrayList<String>();
    }




    // TODO - TURN ON OR OFF broadcasting under a button - API

}
//
