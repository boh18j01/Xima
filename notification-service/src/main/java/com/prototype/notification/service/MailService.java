package com.prototype.notification.service;

import com.prototype.notification.dto.NotificationDto;
import com.prototype.notification.dto.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender sender;

    public void sendMails(NotificationDto dto) {

        //TODO get user email from the user-service using the userId from the dto
        String to = "mhossona@gmail.com";

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(dto.getEmailFrom());
        message.setTo(to);
        message.setSubject(dto.getSubject());
        message.setText(dto.getContent() + dto.getType());

        sender.send(message);
    }


}
