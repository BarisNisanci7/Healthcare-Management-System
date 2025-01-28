package com.example.queueservice.consumer;

import com.example.queueservice.model.QueueMessage;
import org.springframework.stereotype.Service;

// Service to consume messages from the queue
@Service
public class QueueConsumer {

    // Method to process incoming messages
    public void receiveMessage(QueueMessage message) {
        System.out.println("Message received: " + message);
        // Add logic here to handle the message (e.g., send notifications, log data, etc.)
    }
}