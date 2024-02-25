package com.company.Clean.Bot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		servers = @Server(
				url = "http://147.45.104.116:8007"
		)
)
public class CleanBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanBotApplication.class, args);
	}

}
