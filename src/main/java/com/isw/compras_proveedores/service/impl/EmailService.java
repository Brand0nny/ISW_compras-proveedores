package com.isw.compras_proveedores.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;



@Service
public class EmailService {

   
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        checkConfig();
    }

    private void checkConfig() {
        if (mailSender instanceof JavaMailSenderImpl impl) {
            System.out.println("=== CONFIGURACIÓN SMTP ===");
            System.out.println("Host: " + impl.getHost());
            System.out.println("Port: " + impl.getPort());
            System.out.println("Username: " + impl.getUsername());
            System.out.println("JavaMailProperties: " + impl.getJavaMailProperties());
        } else {
            System.out.println("WARNING: JavaMailSender no es una instancia de JavaMailSenderImpl");
        }
    }

    public void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            
            System.out.println("Enviando email a: " + to);
            mailSender.send(message);
            System.out.println("Email enviado exitosamente!");
        } catch (MessagingException e) {
            System.err.println("Error al enviar email:");
            e.printStackTrace();
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage(), e);
        }
    }
}