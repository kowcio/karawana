package karawana.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http


//        http
//                .csrf().disable()
//                .formLogin()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .permitAll()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().migrateSession()
//                .expiredUrl("/sessionExpired.html")
//                .invalidSessionUrl("/invalidSession.html");
//                .maxSessionsPreventsLogin(true)
//                .and()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .sessionFixation().migrateSession()
//                .sessionFixation().none()
//                .maximumSessions(1)

//                .invalidSessionUrl("/");
//                .and().csrf().disable();
;
//        http.headers().frameOptions().disable();

//        http
//                .sessionManagement()
//                .maximumSessions(1)
//                .expiredUrl("/")
//                .maxSessionsPreventsLogin(true)
//                .and()
//                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);



    }

//    @Autowired
//    UserDetailsService userDS;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDS);
//    }
//
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return userDS;
//    }

}