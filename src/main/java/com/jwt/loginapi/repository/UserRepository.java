package com.jwt.loginapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.loginapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	 


	User findByUserName(String UserName);
}
