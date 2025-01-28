package com.example.notificationservice.controller;

import com.example.notificationservice.model.Notification;
import com.example.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// REST Controller for managing notifications
@RestController
@RequestMapping("/api/v1/notifications") // Base path for notification-related endpoints
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    // Endpoint to send a notification
    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody Notification request) {
        notificationService.sendNotification(request);
        return ResponseEntity.ok("Notification sent successfully.");
    }
}