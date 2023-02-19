package com.example.shelve.services.impl;

import com.example.shelve.dto.request.PushNotificationRequest;
import com.example.shelve.services.FCMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationService {
    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);

    @Autowired
    private FCMService fcmService;

    public void sendNotificationToToken(PushNotificationRequest request){
        try{
            fcmService.sendMessageToToken(request);
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
