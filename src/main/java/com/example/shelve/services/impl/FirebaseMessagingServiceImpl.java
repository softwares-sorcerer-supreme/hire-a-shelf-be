package com.example.shelve.services.impl;

import com.example.shelve.dto.request.PushNotificationRequest;
import com.example.shelve.dto.response.NotificationResponse;
import com.example.shelve.services.FCMService;
import com.example.shelve.services.FirebaseMessagingService;
import com.google.firebase.messaging.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirebaseMessagingServiceImpl implements FirebaseMessagingService {

    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);
    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @Autowired
    private FCMService fcmService;

    public FirebaseMessagingServiceImpl(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    @Override
    public void sendNotificationToToken(PushNotificationRequest request) {
        try{
            fcmService.sendMessageToToken(request);
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }


    @Override
    public NotificationResponse getNotificationByAccountId(Long id) {
        return null;
    }

    @Override
    public void sendNotifications(String title, String body, List<String> tokens) throws FirebaseMessagingException {
        MulticastMessage message = MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .addAllTokens(tokens)
                .build();

        BatchResponse response = firebaseMessaging.sendMulticast(message);

        System.out.println("Notifications sent successfully: " + response.getSuccessCount());
    }
}
