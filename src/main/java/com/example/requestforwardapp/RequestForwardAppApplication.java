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
    private String QueryAppHost;
    @Value("${resource.server.order-app.host}")
    private String OrderAppHost;
    @Value("${resource.server.product-app.host}")
    private String ProductAppHost;

    public static void main(String[] args) {
        SpringApplication.run(RequestForwardAppApplication.class, args);
    }
    @Bean
    WebClient webClientQueryApp() {
        return WebClient.builder()
                .baseUrl(QueryAppHost)
                .build();
    }

    @Bean
    WebClient webClientOrderApp() {
        return WebClient.builder()
                .baseUrl(OrderAppHost)
                .build();
    }

    @Bean
    WebClient webClientProductAppHost() {
        return WebClient.builder()
                .baseUrl(ProductAppHost)
                .build();
    }

}
