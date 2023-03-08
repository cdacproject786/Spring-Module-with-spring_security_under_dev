package com.project.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.startup.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.entity.DoctorPrimary;

public class JwtTokenFilter extends OncePerRequestFilter{
	
	@Autowired
	private jwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(!hasAuthorizationBearer(request))
		{
			filterChain.doFilter(request, response);
			return;
		}
		String token = getAccessToken(request);
		
		if(!jwtUtil.validateAccessToken(token))
		{
			filterChain.doFilter(request, response);
			return;
		}
		setAuthenticationContext(token, request);
		filterChain.doFilter(request, response);
	}
	
	private boolean hasAuthorizationBearer(HttpServletRequest request)
	{
		String header = request.getHeader("Authorization");
		if(ObjectUtils.isEmpty(header) || !header.startsWith("Bearer"))
		{
			return false;
		}
		
		return true;
	}
	
	
	private String getAccessToken(HttpServletRequest request)
	{
		String header = request.getHeader("Authorization");
		String token = header.split(" ")[1].trim();
		return token;
	}
	
	private void setAuthenticationContext(String token, HttpServletRequest request)
	{
		UserDetails userDetails = getUSerDetails(token);
		UsernamePasswordAuthenticationToken authentication
		= new UsernamePasswordAuthenticationToken(userDetails,null,null);
		
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	private UserDetails getUSerDetails(String token)
	{
		DoctorPrimary primary = new DoctorPrimary();
		String[] jwtSubject = jwtUtil.getSubject(token).split(",");
		
		primary.setDoctorId(Integer.parseInt(jwtSubject[0]));
		primary.setEmail(jwtSubject[1]);
		return primary;
	}
	
	
	
	
	
	
	
	
	

}
