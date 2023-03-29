package com.project.entity.proxy;

public class LoginResponseProxy {
	
	private String email;
	private String accessToken;
	public LoginResponseProxy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginResponseProxy(String email, String accessToken) {
		super();
		this.email = email;
		this.accessToken = accessToken;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	

}
