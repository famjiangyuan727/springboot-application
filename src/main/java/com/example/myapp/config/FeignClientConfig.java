package com.example.myapp.config;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignClientConfig {

    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(
                5000, // Connect timeout (5 seconds)
                TimeUnit.MILLISECONDS,
                10000, // Read timeout (10 seconds)
                TimeUnit.MILLISECONDS,
                true // Follow redirects
        );
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(
                1000, // Initial interval (1 second)
                2000, // Max interval (2 seconds)
                3 // Max retry attempts
        );
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
