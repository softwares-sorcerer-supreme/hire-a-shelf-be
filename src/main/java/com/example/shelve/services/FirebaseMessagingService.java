package com.example.shelve.services;

import com.example.shelve.dto.request.PushNotificationRequest;

public interface FirebaseMessagingService {

    void sendNotificationToToken(PushNotificationRequest request);

}
