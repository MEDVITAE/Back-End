package org.example.Service;

import jakarta.transaction.Transactional;
import org.example.Domain.Email;
import org.example.Enums.Usuarios.EnumEmail;
import org.example.interfaces.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
@Service
public class EmailService {



    @Autowired
    private JavaMailSender emailSender;

    @Transactional
    public void sendEmail(Email emailModel) {

        emailModel.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            System.out.println(emailModel);
            emailSender.send(message);

            emailModel.setStatusEmail(EnumEmail.SENT);
        } catch (MailException e){
            System.out.println("erro é " + e);
            emailModel.setStatusEmail(EnumEmail.ERROR);
        }
    }
}
