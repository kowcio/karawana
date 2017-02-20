package karawana.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Profile("dev")
@Configuration
public class ActiveProfilesLogger extends Throwable implements ApplicationListener {
    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    Environment env;


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        //redirect na grupe ?

        for (String s : env.getActiveProfiles())
            System.out.println(s);
        for (String s : env.getDefaultProfiles())
            System.out.println(s);

        System.out.println("PROFILES!!! = {}" + env.getProperty("profileActiveMaven"));
        System.out.println("PROFILES!!! = {}" + env.getProperty("activatedProperties"));


    }
}