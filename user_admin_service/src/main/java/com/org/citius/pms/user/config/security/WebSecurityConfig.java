package com.org.citius.pms.user.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.org.citius.pms.user.config.security.exceptionhandler.CustomBasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity // TODO Keeping this simple so API's will be invoked easily, later we can
// enhance the rules
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("citius").password(passwordEncoder().encode("citiusPassword"))
		.roles("USER");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers()
		.frameOptions()
		.disable()
		.and()
		.authorizeRequests()
		.antMatchers("/user/**", "/swagger**")		
		.authenticated()
		.antMatchers("/v2**", "/status/**")
		.permitAll().and().httpBasic()
		.authenticationEntryPoint(customBasicAuthenticationEntryPoint())
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable().cors().disable();
	}

	@Bean
	public AuthenticationEntryPoint customBasicAuthenticationEntryPoint() {
		CustomBasicAuthenticationEntryPoint obj = new CustomBasicAuthenticationEntryPoint();
		obj.setRealmName("PMS User and Admin Realm...");
		return obj;
	}

}