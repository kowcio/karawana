package allinone;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@ComponentScan("allinone")
@EnableAutoConfiguration
@EnableWebSecurity
@EnableTransactionManagement
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Application extends WebMvcConfigurerAdapter {

	private Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {

	    /*//put it in diff class
	    Flyway flyway = new Flyway();
	    flyway.setDataSource(dataSource());
	    flyway.setInitOnMigrate(false);
	    flyway.setSqlMigrationPrefix("V");
	    flyway.setSqlMigrationSuffix(".sql");
	    flyway.migrate();
	    */
		SpringApplication.run(Application.class, args);
	}

	// login controller
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	}

	@Bean
	org.h2.tools.Server h2Server() {
	    org.h2.tools.Server server = new Server();
		try {
			server.runTool("-tcp");
			server.runTool("-tcpAllowOthers");
			// http://www.programcreek.com/java-api-examples/index.php?api=org.h2.tools.Server
			// server.runTool("-tcpPort,8082");
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
	   public static DriverManagerDataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName("org.h2.Driver");
	      dataSource.setUrl("jdbc:h2:./skeledb;AUTO_SERVER=FALSE;IFEXISTS=FALSE;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;MVCC=FALSE");
	      dataSource.setUsername( "sa" );
	      dataSource.setPassword( "" );
	      return dataSource;
	   }


	   
	   
	   

}