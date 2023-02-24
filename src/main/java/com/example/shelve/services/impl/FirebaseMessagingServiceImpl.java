package com.example.shelve.services.impl;

import com.example.shelve.dto.request.PushNotificationRequest;
import com.example.shelve.dto.response.NotificationResponse;
import com.example.shelve.services.FCMService;
import com.example.shelve.services.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessaging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingServiceImpl implements FirebaseMessagingService {

    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);

    @Autowired
    private FCMService fcmService;
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
}
