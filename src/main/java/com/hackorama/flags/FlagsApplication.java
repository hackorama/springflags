package com.hackorama.flags;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.map.repository.config.EnableMapRepositories;

/**
 * Sprint boot application entry point
 *
 * @author Kishan Thomas (kishan.thomas@gmail.com)
 *
 */
@SpringBootApplication
@EnableMapRepositories
public class FlagsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlagsApplication.class, args);
	}

}

