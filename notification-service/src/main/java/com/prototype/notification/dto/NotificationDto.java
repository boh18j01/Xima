package com.prototype.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private String userId;
    private String subject;
    private String content;
    private NotificationType type;
    private String emailFrom;
}
