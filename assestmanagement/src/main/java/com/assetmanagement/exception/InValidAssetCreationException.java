package com.assetmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class InValidAssetCreationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4200780534922408384L;
	public InValidAssetCreationException(String message) {
		super(message);
		
	}

}
