package com.talan.rsa.config;

import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter {
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("pass").roles("ADMIN")
		.and().withUser("user1").password("pass").roles("USER");
		
//		auth.inMemoryAuthentication().withUser("ilyes").password("pass").roles("ADMIN");
//		;
	}

	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/","/login").permitAll()
		//.antMatchers("/chapters").hasAnyAuthority("ROLE_ADMIN")
		.anyRequest().authenticated()
		
		.and().exceptionHandling()
    	.authenticationEntryPoint(new Http401AuthenticationEntryPoint(""))
    	
    	.and()
		.formLogin().loginProcessingUrl("/login")
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
//		.and()
//		.logout()
//		.logoutSuccessUrl("/login");
		//super.configure(http);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	  web.ignoring().antMatchers("/js/**");
	  web.ignoring().antMatchers("/templates/**");
	  web.ignoring().antMatchers("/css/**");
	}

}
