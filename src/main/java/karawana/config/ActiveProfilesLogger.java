package karawana.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Profile("openshift")
@Configuration
@Slf4j
public class ActiveProfilesLogger extends Throwable implements EnvironmentAware {

    @Autowired
    Environment env;


    @Override
    public void setEnvironment(Environment environment) {

        for (String s : env.getActiveProfiles())
            log.info(s);
        for (String s : env.getDefaultProfiles())
            log.info(s);
        log.info("PROFILES!!! = {}" + env.getProperty("profileActiveMaven"));
        log.info("PROFILES!!! = {}" + env.getProperty("activatedProperties"));

    }
}