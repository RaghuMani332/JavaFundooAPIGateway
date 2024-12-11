package com.fundoo.gateway.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	
	@Value("${application.jwt.secretkey}")
	private String secretKey;

	public boolean validateToken(String token) {
	    Claims claims = extractToken(token);  // Extract the Claims object from the token
	    return isTokenExpired(token);  // Return true if the token is not expired
	}

	public boolean isTokenExpired(String token) {
	    Date expirationDate = extractToken(token).getExpiration();  // Get the expiration date from the Claims
	    return expirationDate.before(new Date());  // Token is expired if expiration date is before the current date
	}


//	public String extractUserName(String token) {
//		return extractToken(token).getSubject();
//	}
//

	public Claims extractToken(String token) {
	        try {
	            Claims claims = Jwts.parser()
	                                .verifyWith((SecretKey) getKey())  // Validate using the same signing key
	                                .build()
	                                .parseSignedClaims(token)  // Parse the JWT token
	                                .getPayload();
	            return claims;  // Extract the subject (name) from the claims
	        } catch (JwtException | IllegalArgumentException e) {
	            // Handle exceptions (invalid token, expired token, etc.)
	            throw new RuntimeException("Invalid or expired token", e);
	        }
	    }

	 private Key getKey() {
			byte[] keyBytes = Decoders.BASE64.decode(secretKey);
			return Keys.hmacShaKeyFor(keyBytes);
		}


	
//
//	public String extractEmail(String accessToken) {
//		return extractToken(accessToken).get("email").toString();
//				
//	}
//	

}
