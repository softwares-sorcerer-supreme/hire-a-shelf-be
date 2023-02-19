package com.example.shelve.services;

import com.example.shelve.dto.request.PushNotificationRequest;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Message;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface FCMService {

    void sendMessageToToken(PushNotificationRequest request) throws InterruptedException, ExecutionException;

}
