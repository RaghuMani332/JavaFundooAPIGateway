package com.fundoo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FundooApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundooApiGatewayApplication.class, args);
	}

}
