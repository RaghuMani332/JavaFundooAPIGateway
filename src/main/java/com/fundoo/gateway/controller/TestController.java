package com.fundoo.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	 @GetMapping("/fallback")
	    public ResponseEntity<String> fallback() {
	        return ResponseEntity.ok("Service is temporarily unavailable. Please try again later.");
	    }
}
