package com.example.twst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// import com.example.twst.config.ConfigProd;
import com.example.twst.config.ParameterConfig;

import lombok.extern.slf4j.Slf4j;

// @EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication
@Slf4j
public class TwstApplication implements CommandLineRunner {

	@Autowired
	private ParameterConfig config;

	// @Autowired
	// private ConfigProd configProd;

	public static void main(String[] args) {
		SpringApplication.run(TwstApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(config.toString());
		// log.info(configProd.toString());
	}
}
