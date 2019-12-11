package com.ws.client.service.impl;

import com.ws.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceClient {

    /*
     * The Spring Framework provides an easy abstraction for sending email by using
     * the JavaMailSender interface, and Spring Boot provides auto-configuration for
     * it as well as a starter module.
     */
    private JavaMailSender javaMailSender;

    /**
     *
     * @param javaMailSender
     */
    @Autowired
    public MailServiceClient(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * This function is used to send mail without attachment.
     * @param user
     * @throws MailException
     */

    public void sendEmail(User user) throws MailException {

        /*
         * This JavaMailSender Interface is used to send Mail in Spring Boot. This
         * JavaMailSender extends the MailSender Interface which contains send()
         * function. SimpleMailMessage Object is required because send() function uses
         * object of SimpleMailMessage as a Parameter
         */

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("FormationEsprims@gmail.com");
        mail.setSubject("Mail Inscription");
        //mail.setText("Hurray ! send mail with success...");
        mail.setText("Pré inscription avec succès ,s'il vous plait completer votre inscription par le paiment");

        /*
         * This send() contains an Object of SimpleMailMessage as an Parameter
         */
        javaMailSender.send(mail);
    }

}