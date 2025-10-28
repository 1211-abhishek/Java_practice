package com.example.jwt_interceptor.interceptors;

import com.example.jwt_interceptor.constants.JwtToken;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

public class AuthLoggingFilter implements ExchangeFilterFunction {

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {

        ClientRequest modifiedRequest = ClientRequest.from(request)
                .headers(headers -> headers.setBearerAuth(JwtToken.JWTTOKEN)) // adds "Authorization: Bearer <token>"
                .build();

        System.out.println("➡️ Request: " + modifiedRequest.method() + " " + modifiedRequest.url());
        modifiedRequest.headers().forEach((k, v) -> System.out.println(k + " : " + v));

        return next.exchange(modifiedRequest)
                .doOnNext(response -> {
                    System.out.println("⬅️ Response status: " + response.statusCode());
                });
    }
}
