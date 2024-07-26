package com.jwt.loginapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private String userName;
	
	private String Password;
	
	private String role;
	
	
	

}
