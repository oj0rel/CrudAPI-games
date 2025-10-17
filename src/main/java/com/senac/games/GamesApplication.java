package com.senac.games;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesApplication.class, args);
	}

}
