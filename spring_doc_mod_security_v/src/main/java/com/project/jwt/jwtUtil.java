package com.project.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.project.entity.DoctorPrimary;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class jwtUtil {
	
	private static final long EXPIRE_DURATION = 24*60*60*1000; //24 hours
	
	private static final Logger LOGGER = LoggerFactory.getLogger(jwtUtil.class);
	
	@Value("${app.jwt.secret}")
	private String SECRET_KEY;
	
	public String generateAccessToken(DoctorPrimary primary)
	{
		return
				Jwts.builder()
				.setSubject(String.format("%s%s", primary.getDoctorId(),primary.getEmail()))
				.setIssuer("MedLogApp")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}
	
	public boolean validateAccessToken(String token)
	{
		try
		{
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		}
		catch (ExpiredJwtException ex)
		{
			LOGGER.error("JWT has expired");
		}
		catch (IllegalArgumentException ex)
		{
			LOGGER.error("Token is empty");
		}
		catch (MalformedJwtException ex)
		{
			LOGGER.error("Invalid JWT");
		}
		catch (UnsupportedJwtException ex)
		{
			LOGGER.error("JWT is not supported");
		}
		catch(SignatureException ex)
		{
			LOGGER.error("Signature invalid");
		}
		return false;
	}
	
	public String getSubject(String token)
	{
		return parseClaims(token).getSubject();
	}
	
	public Claims parseClaims(String token)
	{
		return
				Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
	}

}
