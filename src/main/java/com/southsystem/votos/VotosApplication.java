package com.southsystem.votos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class VotosApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotosApplication.class, args);
	}

}
