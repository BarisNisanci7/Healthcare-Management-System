package com.example.notificationservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuration class for RabbitMQ messaging
@Configuration
public class RabbitMQConfig {

    // Declares a persistent queue for notifications
    @Bean
    public Queue notificationQueue() {
        return new Queue("notificationQueue", true); // Persistent queue
    }
}