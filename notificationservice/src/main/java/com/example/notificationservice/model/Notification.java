package com.example.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;
    private String recipientEmail;
    private String subject;
    private String message;

    public Notification(String id, String recipientEmail, String subject, String message) {
        this.id = id;
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.message = message;
    }
}
