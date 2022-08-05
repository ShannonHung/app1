package com.example.requestforwardapp.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@Data
public class ReuqestApp4 {
    @Autowired
    WebClient app4;

    public <T> T getMethod(Class<T> responseClass, Function<UriBuilder, URI> uri){
        return this.app4
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(responseClass)
                .block();
    }

    public <T> List<T> getListMethod(Class<T> responseClass, Function<UriBuilder, URI> uri){
        return this.app4
                .get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(responseClass)
                .collect(Collectors.toList())
                .block();
    }

    public <T,S> T postMethod(Class<T> responseClass, Function<UriBuilder, URI> uri, S requestObject){
        return this.app4
                .post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(requestObject), responseClass)
                .retrieve()
                .bodyToMono(responseClass)
                .block();
    }

    public <T,S> T postMethod(Class<T> responseClass, Function<UriBuilder, URI> uri){
        return this.app4
                .post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(responseClass)
                .block();
    }

    public <T,S> List<T> postListMethod(Class<T> responseClass, Function<UriBuilder, URI> uri, S requestObject){
        return this.app4
                .post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(requestObject), responseClass)
                .retrieve()
                .bodyToFlux(responseClass)
                .collect(Collectors.toList())
                .block();
    }


    public <T,S> T updateMethod(Class<T> responseClass, Function<UriBuilder, URI> uri, S requestObject){
        return this.app4
                .patch()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(requestObject), responseClass)
                .retrieve()
                .bodyToMono(responseClass)
                .block();
    }
    public <T,S> List<T> updateListMethod(Class<T> responseClass, Function<UriBuilder, URI> uri, S requestObject){
        return this.app4
                .patch()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(requestObject), responseClass)
                .retrieve()
                .bodyToFlux(responseClass)
                .collect(Collectors.toList())
                .block();
    }

    public <T> T deleteMethod(Class<T> responseClass, Function<UriBuilder, URI> uri){
        return this.app4
                .delete()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(responseClass)
                .block();
    }


    public <T> T uploadFileMethod(Class<T> responseClass, Function<UriBuilder, URI> uri, MultipartBodyBuilder multipartBodyBuilder) {
        return this.app4
                .post()
                .uri(uri)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .bodyValue(multipartBodyBuilder.build())
                .retrieve()
                .bodyToFlux(responseClass)
                .blockLast();
    }

    public <T> ResponseEntity<T> downloadFileMethod(Class<T> responseClass, Function<UriBuilder, URI> uri){
        return this.app4
                .get()
                .uri(uri)
                .retrieve()
                .toEntity(responseClass)
                .block();
    }

}
