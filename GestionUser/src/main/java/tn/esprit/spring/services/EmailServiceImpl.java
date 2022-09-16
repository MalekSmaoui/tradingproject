package tn.esprit.spring.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    public JavaMailSender emailSender;

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;
    public  void EmailService(JavaMailSender emailSender) {
    	this.emailSender=emailSender;
    }
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
    	        SimpleMailMessage message = new SimpleMailMessage(); 
    	        message.setFrom("sleimi.maryem@gmail.com");
    	        message.setTo(to);
    	        message.setSubject(subject); 
    	        message.setText(text);
    	        emailSender.send(message);
  
    }
}
