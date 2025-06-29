package org.backend.skillywilly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${app.base-url}")
    private String baseUrl;

    public void sendVerificationEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(email);
        message.setSubject("Bestätigen Sie Ihre E-Mail-Adresse für SkillWilly");

        String verificationLink = baseUrl + "/api/users/verify?token=" + token;

        String emailText = "Hallo,\n\n" +
                "Vielen Dank für Ihre Registrierung bei SkillWilly! " +
                "Bitte klicken Sie auf den folgenden Link, um Ihre E-Mail-Adresse zu bestätigen:\n\n" +
                verificationLink + "\n\n" +
                "Der Link ist 24 Stunden gültig.\n\n" +
                "Mit freundlichen Grüßen,\n" +
                "Das SkillWilly-Team";

        message.setText(emailText);
        mailSender.send(message);
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}