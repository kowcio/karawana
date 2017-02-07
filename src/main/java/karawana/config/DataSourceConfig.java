package karawana.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Created by U6041021 on 02/02/2017.
 */
@Configuration
public class DataSourceConfig {
    private final static Logger log = Logger.getLogger(DataSourceConfig.class);

//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public BasicDataSource dataSource() throws URISyntaxException {
//        String dbUrl = System.getenv("JDBC_DATABASE_URL");
//        String username = System.getenv("JDBC_DATABASE_USERNAME");
//        String password = System.getenv("JDBC_DATABASE_PASSWORD");
//        log.warn("Logging into = {}" + dbUrl);
//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setUrl(dbUrl);
//        basicDataSource.setUsername(username);
//        basicDataSource.setPassword(password);
//        log.warn("user = {}" + username);
//
//        return basicDataSource;
//    }
}
