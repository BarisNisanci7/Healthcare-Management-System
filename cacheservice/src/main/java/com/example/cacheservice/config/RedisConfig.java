package com.example.cacheservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

// Configuration class for Redis
@Configuration
public class RedisConfig {

    // Creates a RedisConnectionFactory using Lettuce (default Redis client)
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory("localhost", 6379); // Connect to Redis at localhost:6379
    }

    // Creates a RedisTemplate for interacting with Redis
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory()); // Set the Redis connection factory
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class)); // Serialize values as strings
        return template;
    }
}