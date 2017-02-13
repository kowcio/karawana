package karawana.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**", "/css/**",
                        "/js/**", "/fonts/**", "/console", "console/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .and()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .invalidSessionUrl("/");
    }
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
//
//}