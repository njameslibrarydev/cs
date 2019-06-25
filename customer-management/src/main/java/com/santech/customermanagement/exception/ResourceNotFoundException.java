package com.santech.customermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Resource Not Found Exception Handler
 * @author Nikhil James
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="No matching record found")
public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}