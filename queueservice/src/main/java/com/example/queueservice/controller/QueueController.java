package com.example.queueservice.controller;

import com.example.queueservice.model.QueueMessage;
import com.example.queueservice.producer.QueueProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/queue")
@RequiredArgsConstructor
public class QueueController {

    private final QueueProducer queueProducer;

    // Endpoint to send a message to the queue
    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody QueueMessage message) {
        queueProducer.sendMessage(message); // Sends the message to the queue
        return ResponseEntity.ok("Message sent successfully.");
    }
}
