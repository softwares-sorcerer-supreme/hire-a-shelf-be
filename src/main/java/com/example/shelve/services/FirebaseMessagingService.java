package com.example.shelve.services;

import com.example.shelve.dto.request.PushNotificationRequest;
import com.example.shelve.dto.response.NotificationResponse;
import com.google.firebase.messaging.FirebaseMessagingException;

import java.util.List;

public interface FirebaseMessagingService {

    void sendNotificationToToken(PushNotificationRequest request);

    NotificationResponse getNotificationByAccountId(Long id);

    void sendNotifications(String title, String body, List<String> tokens) throws FirebaseMessagingException;

}
