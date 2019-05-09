//package karawana.config;
//
//import karawana.entities.Group;
//import karawana.entities.User;
//import karawana.utils.TestObjectFabric;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.http.MediaType;
//import org.springframework.session.ReactiveMapSessionRepository;
//import org.springframework.session.ReactiveSessionRepository;
//import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.annotation.SessionScope;
//import org.springframework.web.reactive.function.server.RequestPredicates;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
//import java.util.concurrent.ConcurrentHashMap;
//
//@EnableSpringWebSession
//
//@Component
//public class ConfigBeans {
//
//
//    @Bean
//    public ReactiveSessionRepository reactiveSessionRepository() {
//        return new ReactiveMapSessionRepository(new ConcurrentHashMap<>());
//    }
//
////    @Bean(name = "user")
////    @SessionScope
////    public User user() {
////        return TestObjectFabric.getUser("user from bean 12af");
////    }
//
////    @Bean(name = "userBean")
////    @SessionScope
////    public UserBean userBean() {
////        return new UserBean();
////    }
////
////    @Bean
////    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
////    public Group group() {
////        return TestObjectFabric.getGroupEmpty();
////    }
//
//}