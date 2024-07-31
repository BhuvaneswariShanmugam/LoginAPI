package com.jwt.loginapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.jwt.loginapi.dto.ResponseDTO;
import com.jwt.loginapi.entity.User;
import com.jwt.loginapi.repository.UserRepository;
import com.jwt.loginapi.util.Constances;


@Service
public class AuthenticationService implements UserDetailsService{
	
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) {
		User user = userRepo.findByUserName(userName);
	    return user;
	}
	

	public ResponseDTO SignUp(User user) {
		User obj=new User();
		obj.setUserName(user.getUsername());
		obj.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		obj.setRole(user.getRole());
		obj.setEmailId(user.getEmailId());
		obj.setPhNo(user.getPhNo());
		
		ResponseDTO responseDto=new ResponseDTO();
		responseDto.setMessage(Constances.CREATED);
		responseDto.setData(userRepo.save(obj));
		responseDto.setStatusCode(200);
		return responseDto;
	}
	
	


//	public UserDTO getUserDetail(String userName) {
//		User user=userRepo.findByUserName(userName);
//		if(user.getUsername().equals(Role.USER)) {
//			userDto.setUserName(user.getUsername());
//			userDto.setPassword(user.getPassword());
//			userDto.setRole(user.getRole().name());
//			return userDto;
//		}
//		return null;
//	}

	
	
}