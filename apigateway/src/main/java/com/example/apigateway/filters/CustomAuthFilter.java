package com.example.apigateway.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// A global filter for authentication
@Component
@RequiredArgsConstructor
public class CustomAuthFilter implements org.springframework.cloud.gateway.filter.GlobalFilter {

    // The filter method for processing each request
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();

        // Check if the Authorization header is present
        if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED); // Respond with 401 Unauthorized
            return exchange.getResponse().setComplete();
        }

        // Retrieve and validate the token
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (!validateToken(token)) {
            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.FORBIDDEN); // Respond with 403 Forbidden if token is invalid
            return exchange.getResponse().setComplete();
        }

        // Proceed with the request if the token is valid
        return chain.filter(exchange);
    }

    // Dummy token validation logic
    private boolean validateToken(String token) {
        return token != null && token.startsWith("Bearer "); // Validate the token format (e.g., "Bearer <token>")
    }
}