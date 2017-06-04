package com.katalyst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"com.katalyst.controller"})
@SpringBootApplication
public class ApparelMagicClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApparelMagicClientApplication.class, args);
	}
}
