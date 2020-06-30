package com.sample.spring.boot.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig implements WebMvcConfigurer {
	
	// creating static nested class
	@Configuration
	public class securityInit extends WebSecurityConfigurerAdapter {
		
		// to create users to access the application we can user in-memory authentication 
		// for configure method either we can pass AuthenticationManagerBuilder or HttpSecurity
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication()
				.withUser("user1").password("{noop}user1").authorities("user").and()
				.withUser("user2").password("{noop}user2").authorities("admin");
		}
	
	}

}
