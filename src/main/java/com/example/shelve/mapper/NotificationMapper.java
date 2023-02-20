package com.example.shelve.mapper;

import com.example.shelve.dto.response.NotificationResponse;
import com.example.shelve.entities.Notification;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class NotificationMapper {
    public NotificationResponse toNotificationResponse (Notification notification){
        NotificationResponse notificationResponse = NotificationResponse.builder()
                .body(notification.getBody())
                .title(notification.getTitle())
                .account(notification.getAccount())
                .build();
        return notificationResponse;
    }
}
