package com.assetmanagement.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class VendorNotFoundException extends RuntimeException {

	public VendorNotFoundException(String message) {
		super(message);
		
	}
	
	

}