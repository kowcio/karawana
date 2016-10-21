package karawana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import karawana.service.UserService;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/**", "/all", "/console/**", "/login/**", "/css/**", "/images/**", "/js/**", "/fonts/**")
                .permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/success", true).failureUrl("/login?error").and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).and().exceptionHandling()
                .accessDeniedPage("/login?error")
        
        ;
        
        // @formatter:on
        
    }
    
    @Autowired
    HikariDataSource restDataSource;
    
    /*
     * @Autowired public void configure(AuthenticationManagerBuilder auth)
     * throws Exception { auth .jdbcAuthentication() .dataSource(restDataSource)
     * ;//.passwordEncoder( new ShaPasswordEncoder() ); }
     */
    
    // http://docs.spring.io/autorepo/docs/spring-security/3.2.4.RELEASE/apidocs/org/springframework/security/crypto/bcrypt/BCrypt.html
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    UserService userService;
    
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder())
        
        ;
        
    }
    
    private String getUserQuery() {
        return "SELECT login as username, senha as password " + "FROM usuario " + "WHERE login = ?";
    }
    
    private String getAuthoritiesQuery() {
        return "SELECT DISTINCT usuario.login as username, autorizacao.descricao as authority "
                + "FROM usuario, autorizacao_usuario, autorizacao "
                + "WHERE usuario.id = autorizacao_usuario.fk_usuario "
                + "AND autorizacao.id = autorizacao_usuario.fk_autorizacao " + "AND usuario.login = ? ";
    }
    
    /*
     * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
     * throws Exception {
     * auth.inMemoryAuthentication().withUser("user").password
     * ("password").roles("USER"); }
     */
    
}
