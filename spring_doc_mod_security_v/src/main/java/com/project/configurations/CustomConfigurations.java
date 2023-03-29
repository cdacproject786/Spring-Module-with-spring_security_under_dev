package com.project.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.Repository.DoctorPrimaryRepository;
import com.project.Service.EmailSenderUtilsService;
import com.project.jwt.JwtTokenFilter;
import com.project.jwt.jwtUtil;

@Configuration
public class CustomConfigurations {

	@Bean
	public EmailSenderUtilsService getEmailService()
	{
		return new EmailSenderUtilsService();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtTokenFilter jwtTokenFilter()
	{
		return new JwtTokenFilter();
	}
	
	@Bean
	public jwtUtil jwtUtil()
	{
		return new jwtUtil();
	}
	
	
}
