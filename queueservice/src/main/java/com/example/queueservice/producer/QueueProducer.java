package com.example.queueservice.producer;

import com.example.queueservice.config.RabbitMQConfig;
import com.example.queueservice.model.QueueMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueProducer {

    private final RabbitTemplate rabbitTemplate;

    // Method to send a message to the queue
    public void sendMessage(QueueMessage message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, message); // Send message to the queue
        System.out.println("Message sent: " + message); // Log the sent message
    }
}
