package karawana;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
/*
 * 4 in 1
 *
 * @Configuration
 *
 * @EnableAutoConfiguration
 *
 * @EnableWebMvc
 *
 * @ComponentScan
 */
@EnableJpaAuditing
@EnableScheduling
@EnableCaching
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"karawana.repositories"})
public class Application extends SpringBootServletInitializer {
    final static Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

}
