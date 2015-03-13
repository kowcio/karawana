package skele;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import skele.service.UserService;


@ComponentScan("skele")
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
 class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/**","/all", "/console/**", "/login/**", "/css/**", "/images/**", "/js/**", "/fonts/**").permitAll()
			.anyRequest().authenticated()
			
		.and()
			.formLogin()
			.loginPage("/login").defaultSuccessUrl("/", true).failureUrl("/login?error")
			
		.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			
		.and()
			.exceptionHandling()
			.accessDeniedPage("/login?error")
			;
		// @formatter:on
	
		
}
}

