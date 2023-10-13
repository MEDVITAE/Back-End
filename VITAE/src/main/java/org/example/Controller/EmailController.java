package org.example.Controller;

import jakarta.validation.Valid;
import org.example.Records.emailDto;
import org.example.Domain.Email;
import org.example.Service.EmailService;
import org.example.interfaces.EmailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    EmailService emailService;
    @Autowired
    EmailRepository emailRepository;

    @PostMapping
    @PreAuthorize(" hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    public ResponseEntity<Email> enviarEmail(@RequestBody @Valid emailDto requestEmail){
        Email email = new Email();

        BeanUtils.copyProperties(requestEmail,email);
        emailService.sendEmail(email);
        emailRepository.save(email);

                return new ResponseEntity<>(email, HttpStatus.CREATED);

    }
}
