package com.abit.Abit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.abit.Abit.config.WebSecurityConfig;

@SpringBootApplication
public class AbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbitApplication.class, args);

		
	}

}
