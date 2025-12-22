package com.chess.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ChessBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChessBackendApplication.class, args);
	}

}
