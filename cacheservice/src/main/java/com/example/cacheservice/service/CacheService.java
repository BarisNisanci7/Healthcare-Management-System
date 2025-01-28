package com.example.cacheservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    // Save data to the cache with a specified time-to-live (TTL)
    public void save(String key, Object value, long ttl) {
        redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS); // Set the value with a TTL in seconds
    }

    // Retrieve data from the cache by key
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key); // Get the value by key
    }

    // Delete data from the cache by key
    public void delete(String key) {
        redisTemplate.delete(key); // Delete the key-value pair from the cache
    }
}
