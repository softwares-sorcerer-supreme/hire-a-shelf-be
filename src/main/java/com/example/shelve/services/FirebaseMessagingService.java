package com.example.shelve.services;

import com.example.shelve.dto.request.PushNotificationRequest;
import com.example.shelve.dto.response.NotificationResponse;

public interface FirebaseMessagingService {

    void sendNotificationToToken(PushNotificationRequest request);

    NotificationResponse getNotificationByAccountId(Long id);

}
