package com.jpa.basic.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JpaBasicTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaBasicTestApplication.class, args);
	}
}
