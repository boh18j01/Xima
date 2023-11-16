package com.prototype.notification.controller;

import com.prototype.notification.dto.NotificationDto;
import com.prototype.notification.dto.NotificationType;
import com.prototype.notification.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailService service;



    @PostMapping("mail")
    public void index(@RequestBody NotificationDto dto){
        service.sendMails(dto);
    }
}
