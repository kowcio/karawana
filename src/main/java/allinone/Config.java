package allinone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import allinone.service.UserService;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class Config extends GlobalAuthenticationConfigurerAdapter {
    
    // @Autowired
    // private UserService userService;
    
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        System.err.println("INIT in Authentication manager ! ");
        // auth.userDetailsService(userService);
    }
}
