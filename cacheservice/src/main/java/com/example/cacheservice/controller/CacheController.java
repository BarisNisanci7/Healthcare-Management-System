package com.example.cacheservice.controller;

import com.example.cacheservice.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cache")
@RequiredArgsConstructor
public class CacheController {

    private final CacheService cacheService;

    // Endpoint to save data to the cache
    @PostMapping("/save")
    public ResponseEntity<String> saveToCache(@RequestParam String key, @RequestParam String value, @RequestParam long ttl) {
        cacheService.save(key, value, ttl); // Save key-value pair with a time-to-live
        return ResponseEntity.ok("Data saved to cache successfully.");
    }

    // Endpoint to retrieve data from the cache
    @GetMapping("/get")
    public ResponseEntity<Object> getFromCache(@RequestParam String key) {
        Object value = cacheService.get(key); // Retrieve value by key
        return value != null ? ResponseEntity.ok(value) : ResponseEntity.notFound().build(); // Return value or 404
    }

    // Endpoint to delete data from the cache
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFromCache(@RequestParam String key) {
        cacheService.delete(key); // Delete the key-value pair from the cache
        return ResponseEntity.ok("Data deleted from cache successfully.");
    }
}
