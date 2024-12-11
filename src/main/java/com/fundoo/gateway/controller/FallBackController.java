package com.fundoo.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {
	
	   @RequestMapping("/userFallBack")
	    public ResponseEntity<String> userServiceFallBack() {
	        return new ResponseEntity<String>("user Service is taking too long to respond or server down. Please try again later",HttpStatus.SERVICE_UNAVAILABLE);
	    }
	   @RequestMapping("/notesFallBack")
	    public ResponseEntity<String> notesServiceFallBack() {
	        return new ResponseEntity<String>("notes Service is taking too long to respond or server down. Please try again later",HttpStatus.SERVICE_UNAVAILABLE);
	    }
	   @RequestMapping("/lableFallBack")
	    public ResponseEntity<String> lableServiceFallBack() {
	        return new ResponseEntity<String>("lable Service is taking too long to respond or server down. Please try again later",HttpStatus.SERVICE_UNAVAILABLE);
	    }
	    
}
