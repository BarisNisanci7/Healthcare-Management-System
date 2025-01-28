package com.example.queueservice.config;

import com.example.queueservice.consumer.QueueConsumer;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuration class for RabbitMQ
@Configuration
public class RabbitMQConfig {

    // Name of the queue
    public static final String QUEUE_NAME = "prescriptionQueue";

    // Declare a RabbitMQ queue
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false); // Non-durable queue
    }

    // Configure a listener container for processing messages
    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory); // RabbitMQ connection factory
        container.setQueueNames(QUEUE_NAME); // Queue name to listen to
        container.setMessageListener(listenerAdapter); // Listener for incoming messages
        return container;
    }

    // Message listener adapter to delegate message processing to the QueueConsumer
    @Bean
    public MessageListenerAdapter listenerAdapter(QueueConsumer consumer) {
        return new MessageListenerAdapter(consumer, "receiveMessage"); // Method to handle messages
    }
}
