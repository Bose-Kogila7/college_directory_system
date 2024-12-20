package com.cts.cda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cts.cda.controller", "com.cts.cda.service", "com.cts.cda.model", "com.cts.cda.exceptions","com.cts.cda.security"})
public class CollegeDirectoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeDirectoryApplication.class, args);
	}

}
