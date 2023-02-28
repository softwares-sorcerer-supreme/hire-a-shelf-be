package com.example.shelve.mapper;

import com.example.shelve.dto.response.AccountResponse;
import com.example.shelve.dto.response.NotificationResponse;
import com.example.shelve.entities.Notification;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    @Autowired
    AccountMapper mapper;
    public NotificationResponse toNotificationResponse (Notification notification){
        NotificationResponse notificationResponse = NotificationResponse.builder()
                .body(notification.getBody())
                .title(notification.getTitle())
                .accountResponse(mapper.toAccountResponse(notification.getAccount()))
                .build();
        return notificationResponse;
    }
}
