package com.jwt.loginapi.controller;

import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jwt.loginapi.config.TokenProvider;
import com.jwt.loginapi.dto.JwtDTO;
import com.jwt.loginapi.dto.RefreshTokenDTO;
import com.jwt.loginapi.dto.ResponseDTO;
import com.jwt.loginapi.dto.SignInDTO;
import com.jwt.loginapi.entity.User;
import com.jwt.loginapi.service.AuthenticationService;
import com.jwt.loginapi.util.Constances;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

	@Autowired
	private TokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/sign-up")
	public ResponseDTO SignUp(@RequestBody User user) {
		return authenticationService.SignUp(user);

	}

	@PostMapping("/sign-in")
	public ResponseDTO SignIn(@RequestBody SignInDTO user) throws AuthenticationException {
		var userNamePassword = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
		var authorizedUser = authenticationManager.authenticate(userNamePassword);
		var accessToken = tokenProvider.generateAccessToken((User) authorizedUser.getPrincipal());
		var refreshToken = tokenProvider.generateRefreshToken((User) authorizedUser.getPrincipal());

		
		ResponseDTO responseDto = new ResponseDTO();
		responseDto.setMessage(Constances.RETRIEVED);
		responseDto.setData(new JwtDTO(accessToken,refreshToken));
		responseDto.setStatusCode(200);
		return responseDto;

	}
	
	
	@PostMapping("/refresh")
	 public ResponseDTO refreshAccessToken(@RequestBody RefreshTokenDTO request) {
        try {
        	String newAccessToken = tokenProvider.refreshAccessToken(request.getRefreshToken());
        	String refreshToken = request.setRefreshToken(newAccessToken);
        	ResponseDTO responseDto= new ResponseDTO();
        	responseDto.setMessage(Constances.CREATED);
        	responseDto.setData(refreshToken);
        	responseDto.setStatusCode(200);
        	return responseDto;
        } catch (Exception e) {
        	ResponseDTO responseDto= new ResponseDTO();
        	responseDto.setMessage(Constances.NOT_FOUND);
        	responseDto.setData("Invalid refresh token");
        	responseDto.setStatusCode(401);
        	return responseDto;
        }
        }
	
	


//	@GetMapping("/user/{userName}")
//	public UserDTO getUserDetial(@PathVariable String userName) {
//		return authenticationService.getUserDetail(userName);
//	}
}