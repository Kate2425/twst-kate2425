package com.example.twst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.example.twst" })
public class TwstApplication {
	public static void main(String[] args) {
		SpringApplication.run(TwstApplication.class, args);
	}
}
