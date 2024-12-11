package com.fundoo.gateway.filter;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.fundoo.gateway.exception.UnautorizedException;
import com.fundoo.gateway.util.JwtUtil;

@Component	
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{


	@Autowired
	private RouteValidator validator;

	@Autowired
	private JwtUtil util;


	public static class Config {

	}
	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {

		return ((exchange,chain) -> {
			if(validator.isSecured.test(exchange.getRequest()))
			{
				Set<String> keySet = exchange.getRequest().getCookies().keySet();

				String token =null;
				for (String key : keySet) {
					token = exchange.getRequest().getCookies().get(key).get(0).getValue(); //value
				}
				if(token ==null)
					throw new UnautorizedException("missing authorization cookie");

				boolean b = util.validateToken(token);

				if(b)
					throw new UnautorizedException("invalid token or token expired");


			}
			return chain.filter(exchange);


		});
	}

}
