package com.project.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
/*	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception 
	{
		return 
				http
				.csrf(csrf->csrf.disable())
				.authorizeRequests(auth -> {
					auth.antMatchers("/").permitAll();
					auth.antMatchers("/doctor/***").permitAll();
					auth.antMatchers("/patient/***").permitAll();
					auth.antMatchers("/admin/***").permitAll();
				})
				.httpBasic(Customizer.withDefaults())
				.build();
	}*/
	
	@Autowired
	UserDetailsService userDetailsService;
	
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.userDetailsService(userDetailsService); }
	 */
	
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.authorizeHttpRequests()
		.antMatchers("/**").permitAll();
		//.antMatchers("/admin/***").permitAll()
		//.antMatchers("/patient/***").permitAll()
		//.and()
		//.httpBasic();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

}
