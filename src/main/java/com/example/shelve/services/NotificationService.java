package com.example.shelve.services;

import com.example.shelve.dto.response.NotificationResponse;
import com.example.shelve.entities.Notification;

import java.util.List;

public interface NotificationService {

    List<NotificationResponse> getNotificationByAccountId(Long id);
    NotificationResponse addANotification(String title, String body, Long storeId);
    NotificationResponse addNotificationByBrand(String title, String body, Long storeId);
    NotificationResponse addNotificationByStore(String title, String body, Long storeId);

}
