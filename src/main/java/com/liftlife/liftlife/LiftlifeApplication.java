package com.liftlife.liftlife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.liftlife")
public class LiftlifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiftlifeApplication.class, args);
	}

	
	
}
