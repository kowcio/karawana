package allinone;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.config.CacheConfiguration;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zaxxer.hikari.HikariDataSource;

//todo check those
@SpringBootApplication //4 in 1
//@Configuration
//@EnableAutoConfiguration
//@EnableWebMvc
//@ComponentScan

@EnableConfigurationProperties
@PropertySource("classpath:/application.properties")
@EnableJpaAuditing
@EnableScheduling
@EnableJpaRepositories
@EnableCaching
@EnableTransactionManagement
public class Application extends WebMvcConfigurerAdapter {
    
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        
        SpringApplication.run(Application.class, args);
    }
    
    // login controller
    /*
     * @Override public void addViewControllers(ViewControllerRegistry registry)
     * { }
     */
    @Bean
    org.h2.tools.Server h2Server() {
        log.info("Loading h2 server.");
        org.h2.tools.Server server = new Server();
        try {
            server.runTool("-tcp");
            server.runTool("-tcpAllowOthers");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return server;
    }
    
    @Bean
    public ApplicationSecurity applicationSecurity() {
        return new ApplicationSecurity();
    }
    
    @Bean
    @Primary
    public static HikariDataSource dataSource() {
        int maxPoolSize = 10;
        boolean isAutoCommit = false;
        
        HikariDataSource dataSource = new HikariDataSource();
        
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource
                .setJdbcUrl("jdbc:h2:./skeledb;AUTO_SERVER=FALSE;IFEXISTS=FALSE;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        dataSource.setConnectionTestQuery("select 1");
        dataSource.setMaximumPoolSize(maxPoolSize);
        dataSource.setAutoCommit(isAutoCommit);
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        
        return dataSource;
    }
    
    //http://stackoverflow.com/questions/21944202/using-ehcache-in-spring-4-without-xml
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<Cache>();
        caches.add(new ConcurrentMapCache("myCache"));
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    
}
