package com.jwt.loginapi.dto;

import lombok.Data;

@Data
public class JwtDTO {
	
	String accessToken;
	String refreshToken;
	
	
	public JwtDTO(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	
	
	

}
