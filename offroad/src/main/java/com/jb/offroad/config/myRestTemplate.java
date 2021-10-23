package com.jb.offroad.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
@Configuration
public class myRestTemplate {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                //set connection time out for 3 sec
                .setConnectTimeout(Duration.ofMillis(3000))
                //set read time out for 3 sec.
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }
}
