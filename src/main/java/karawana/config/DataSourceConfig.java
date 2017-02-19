package karawana.config;

import org.apache.log4j.Logger;
import org.h2.tools.Server;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.sql.SQLException;

@Configuration
@Profile("default")
public class DataSourceConfig implements ServletContextInitializer {
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


    @Bean
    public Server startH2TcpServer() throws SQLException {
        return Server.createWebServer("-webAllowOthers","-tcpAllowOthers").start();
    }


    public void initH2Console(ServletContext servletContext) {
        log.debug("Initializing H2 console");
        ServletRegistration.Dynamic h2ConsoleServlet =
                servletContext.addServlet(
                        "H2Console", new org.h2.server.web.WebServlet());
        h2ConsoleServlet.addMapping("/console/*");

    }


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        initH2Console(servletContext);
    }
}
