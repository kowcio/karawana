package karawana.service;


import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

public class RabbitTestGeneratorMessages {




    @Scheduled(fixedRate = 5000)
    public boolean sendGeneratedMessageToQueue(){

        List<String> queuesNameForGeneration = new ArrayList<String>();

        //send message to given queue
        return true;

    }


}
