package com.example.notificationservice.utils;

// Utility class for generating HTML email templates
public class EmailTemplateGenerator {

    // Generates a simple HTML email template
    public static String generateNotificationTemplate(String message) {
        return "<html><body><h1>Notification</h1><p>" + message + "</p></body></html>";
    }
}