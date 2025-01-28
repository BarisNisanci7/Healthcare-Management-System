package com.example.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route for Authentication Service
                .route("authentication_service", r -> r.path("/api/v1/auth/**")
                        .uri("http://localhost:8081")) // Forward requests to Authentication Service running at localhost:8081
                // Route for Notification Service
                .route("notification_service", r -> r.path("/api/v1/notifications/**")
                        .uri("http://localhost:8082")) // Forward requests to Notification Service running at localhost:8082
                // Route for Doctor Service
                .route("doctor_service", r -> r.path("/api/v1/doctors/**")
                        .uri("http://localhost:8083")) // Forward requests to Doctor Service running at localhost:8083
                // Route for Pharmacy Service
                .route("pharmacy_service", r -> r.path("/api/v1/pharmacies/**")
                        .uri("http://localhost:8084")) // Forward requests to Pharmacy Service running at localhost:8084
                .build(); // Build all routes
    }
}
