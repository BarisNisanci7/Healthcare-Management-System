package com.example.notificationservice.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

// Service for sending emails
@Service
public class EmailSenderService {

    private final JavaMailSender mailSender;

    // Constructor-based dependency injection
    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // Sends an email
    public void sendEmail(String to, String subject, String text) throws jakarta.mail.MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to); // Recipient email
        helper.setSubject(subject); // Email subject
        helper.setText(text, true); // Email content in HTML format

        mailSender.send(message); // Sends the email
    }
}