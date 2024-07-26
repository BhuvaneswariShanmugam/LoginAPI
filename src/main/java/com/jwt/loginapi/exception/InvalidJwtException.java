package com.jwt.loginapi.exception;

import javax.naming.AuthenticationException;


//@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtException extends AuthenticationException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidJwtException(String ex) {
	    super(ex);
	  }

}



