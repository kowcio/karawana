package karawana.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.permitAll()antMatchers("/login").permitAll()
//                .antMatchers("/**", "/all", "/console/**", "/login/**", "/css/**", "/images/**", "/js/**", "/fonts/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and().exceptionHandling()
//                .accessDeniedPage("/login?error")
//        ;
        http
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .and()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .invalidSessionUrl("/");
        http.antMatcher("/**").authorizeRequests().anyRequest().permitAll();
        http.antMatcher("/**").authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().antMatchers("/console/**").permitAll();
//
//        http.csrf().disable();
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic().disable()
                .csrf().disable();

        http
                .authorizeRequests()
                .anyRequest().permitAll();
        http.authorizeRequests().antMatchers("/", "/**").permitAll();
    }
}