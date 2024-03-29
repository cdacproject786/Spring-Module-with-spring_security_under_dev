package com.project.configurations;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.Repository.DoctorPrimaryRepository;
import com.project.entity.DoctorPrimary;
import com.project.jwt.DoctorDetails;
import com.project.jwt.JwtTokenFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private DoctorPrimaryRepository doctorPrimaryRepository;
	
	@Autowired
	private JwtTokenFilter jwtTokenFilter;
	
	
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception 
	{
		 http.csrf().disable();
		 http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 
		 http.authorizeHttpRequests()
		 .antMatchers("/doctor/login","/doctor/register","/admin/login").permitAll()
		 .anyRequest().authenticated();
		 
		 http.exceptionHandling()
		 .authenticationEntryPoint((request,response,ex) -> {
			 response.sendError(HttpServletResponse.SC_UNAUTHORIZED,ex.getMessage()
			 );
		 });
		 
		 http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		 return http.build();			
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception
	{
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
				DoctorPrimary primary = doctorPrimaryRepository.findByemail(username);
				if(primary == null)
					throw new UsernameNotFoundException("User not present");
				DoctorDetails details = null;
				
			    details = new DoctorDetails(primary.getEmail(),primary.getPwd());
				
				return details;
					
						
			}
		};
	}
	

	


}
