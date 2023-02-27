package com.example.shelve.mapper;

import com.example.shelve.dto.response.NotificationResponse;
import com.example.shelve.entities.Notification;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Builder
public class NotificationMapper {

    @Autowired
    private AccountMapper accountMapper;

    public NotificationResponse toNotificationResponse (Notification notification){
        return NotificationResponse.builder()
                .body(notification.getBody())
                .title(notification.getTitle())
                .account(accountMapper.toAccountResponse(notification.getAccount()))
                .build();
    }
}
