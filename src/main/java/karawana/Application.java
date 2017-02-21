package karawana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableCaching
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"karawana.repositories"})

public class Application implements WebApplicationInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void onStartup(ServletContext container) {
        SpringApplication.run(Application.class);
    }

}