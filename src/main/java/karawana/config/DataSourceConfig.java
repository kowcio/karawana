//package karawana.config;
//
//import org.apache.log4j.Logger;
//import org.h2.tools.Server;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.ServletContextInitializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.core.env.Environment;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
//@Configuration
//public class DataSourceConfig implements ServletContextInitializer {
//    private final Logger log = Logger.getLogger(getClass());
//
//    @Autowired
//    Environment environment;
////    @Bean
////    @Primary
////    @ConfigurationProperties(prefix = "spring.datasource")
////    public BasicDataSource dataSource() throws URISyntaxException {
////        String dbUrl = System.getenv("JDBC_DATABASE_URL");
////        String username = System.getenv("JDBC_DATABASE_USERNAME");
////        String password = System.getenv("JDBC_DATABASE_PASSWORD");
////        log.warn("Logging into = {}" + dbUrl);
////        BasicDataSource basicDataSource = new BasicDataSource();
////        basicDataSource.setUrl(dbUrl);
////        basicDataSource.setUsername(username);
////        basicDataSource.setPassword(password);
////        log.warn("user = {}" + username);
////
////        return basicDataSource;
////    }
//
//    @Profile("dev")
//
//    @Bean
//    public Server startH2TcpServer_lol4() throws Exception {
//        log.info("STARTING H2#######################################");
//        log.info("STARTING H2#######################################");
//        log.info("STARTING H2#######################################");
//
//        return Server.createWebServer("-webAllowOthers", "-tcpAllowOthers").start();
//    }
//
//    @Profile("dev")
//
//    public void initH2Console_lol4(ServletContext servletContext) {
//
//        log.debug("Initializing H2 console");
//        ServletRegistration.Dynamic h2ConsoleServlet =
//                servletContext.addServlet(
//                        "H2Console", new org.h2.server.web.WebServlet());
//        h2ConsoleServlet.addMapping("/console/*");
//
//    }
//
//    @Profile("dev")
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        initH2Console_lol4(servletContext);
//    }
//
////    @Profile("openshift")
////    @Bean(destroyMethod = "close")
////    public DataSource dataSource() {
////        String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
////        String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
////        String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
////        String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
////        String databaseName = System.getenv("OPENSHIFT_APP_NAME");
////        String url = "jdbc:mysql://" + host + ":" + port + "/"+databaseName;
////        BasicDataSource dataSource = new BasicDataSource();
////        dataSource.setDriverClassName("org.MYSQL.Driver");
////        dataSource.setUrl(url);
////        dataSource.setUsername(username);
////        dataSource.setPassword(password);
////        dataSource.setTestOnBorrow(true);
////        dataSource.setTestOnReturn(true);
////        dataSource.setTestWhileIdle(true);
////        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
////        dataSource.setNumTestsPerEvictionRun(3);
////        dataSource.setMinEvictableIdleTimeMillis(1800000);
////        dataSource.setValidationQuery("SELECT version()");
////
////        return dataSource;
////    }
//
//}
