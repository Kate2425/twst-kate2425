package com.example.twst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TwstApplication implements CommandLineRunner {

	@Autowired
	private Config config;

	public static void main(String[] args) {
		SpringApplication.run(TwstApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(config.toString());
	}
}
