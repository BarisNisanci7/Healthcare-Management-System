package com.example.notificationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

// Configuration class for JavaMailSender
@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Configuring the SMTP server
        mailSender.setHost("smtp.gmail.com"); // Gmail SMTP server
        mailSender.setPort(587); // Standard SMTP port for STARTTLS
        mailSender.setUsername("your-email@gmail.com"); // Replace with your email
        mailSender.setPassword("your-email-password"); // Replace with your email password

        // Setting SMTP properties
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true"); // Authentication enabled
        props.put("mail.smtp.starttls.enable", "true"); // Use STARTTLS for security
        props.put("mail.debug", "true"); // Debug mode for detailed logs

        return mailSender;
    }
}