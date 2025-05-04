package com.example.twst.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "spring.datasource")
@Component
public class DataSourceConfig {

    private String url;

    private String username;

    private String password;

    private String driverClassName;

}
