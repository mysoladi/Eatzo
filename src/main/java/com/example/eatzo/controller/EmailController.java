package com.example.eatzo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailController {

    @Autowired
    private JavaMailSender emailSender;

    @GetMapping("/form")
    public String showForm() {
        return "form"; // return form.html
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam String name,
                            @RequestParam String email,
                            @RequestParam String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("recipient_email@example.com"); // Change to your recipient email
        mailMessage.setSubject("New Contact Form Submission");
        mailMessage.setText("Name: " + name + "\nEmail: " + email + "\nMessage: " + message);

        emailSender.send(mailMessage);

        return "redirect:/form?success"; // Redirect back to form
    }
}
