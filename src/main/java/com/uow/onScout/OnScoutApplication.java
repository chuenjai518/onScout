package com.uow.onScout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.uow.*")
public class OnScoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnScoutApplication.class, args);
	}

}
