package com.tourofheroes.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class HeroNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String message;
	
	public HeroNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
