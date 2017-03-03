package karawana.config;

import org.apache.log4j.Logger;
import org.h2.server.web.WebServlet;
import org.h2.tools.Server;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    private final Logger log = Logger.getLogger(getClass());

    @Bean
    public Server startH2TcpServer_lol4() throws Exception {
        log.info("STARTING H2#######################################");
        log.info("STARTING H2#######################################");
        return Server.createWebServer("-webAllowOthers", "-tcpAllowOthers").start();
    }

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        registration.addInitParameter("webAllowOthers", "true");
        registration.addInitParameter("tcpAllowOthers", "true");
        return registration;
    }


}
