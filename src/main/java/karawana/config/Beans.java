package karawana.config;

import karawana.entities.BeanUser;
import karawana.entities.Group;
import karawana.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class Beans {

//
//    @Bean
//    @Scope(value = WebApplicationContext.SCOPE_SESSION,
//            proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public User user() {
//        return new User();
//    }
//
//    @Bean
//    @Scope(value = WebApplicationContext.SCOPE_SESSION,
//            proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public BeanUser beanUser() {
//        return new BeanUser();
//    }
//
//
//    @Bean
//    @Scope(value = WebApplicationContext.SCOPE_SESSION,
//            proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public Group group() {
//        return new Group();
//    }



}
