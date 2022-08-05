package com.example.requestforwardapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@ServletComponentScan
@SpringBootApplication
public class RequestForwardAppApplication {
    @Value("${resource.server.query-app.host}")
    private String app2;
    @Value("${resource.server.order-app.host}")
    private String app4;
    @Value("${resource.server.product-app.host}")
    private String app3;

    public static void main(String[] args) {
        SpringApplication.run(RequestForwardAppApplication.class, args);
    }
    @Bean
    WebClient app2() {
        return WebClient.builder()
                .baseUrl(app2)
                .build();
    }

    @Bean
    WebClient app4() {
        return WebClient.builder()
                .baseUrl(app4)
                .build();
    }

    @Bean
    WebClient app3() {
        return WebClient.builder()
                .baseUrl(app3)
                .build();
    }

}
