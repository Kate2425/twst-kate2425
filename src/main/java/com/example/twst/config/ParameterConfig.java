package com.example.twst.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "myapp.config")
@Component
public class ParameterConfig {

    private String key;

    private String Key2 = null;

    private String envTest;

    private String noenv;

    private boolean flag;

    private String env;
}
