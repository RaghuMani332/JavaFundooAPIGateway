package com.fundoo.gateway.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import com.fundoo.gateway.exception.UnautorizedException;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{

	 private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status, String errorMessage, String rootCause) {
	        return ResponseEntity.status(status).body(ErrorStructure.<String>builder().status(status.value())
	                .message(errorMessage).rootCause(rootCause).build());
	    }

	    @ExceptionHandler(UnautorizedException.class)
	    public ResponseEntity<ErrorStructure<String>> handleNotesNotFound(UnautorizedException ex) {
	        return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "the request is un authorized one please login and try to access");
	    }
}
