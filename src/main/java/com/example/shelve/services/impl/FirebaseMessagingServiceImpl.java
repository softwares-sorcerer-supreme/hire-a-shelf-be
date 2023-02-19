package com.example.shelve.services.impl;

import com.example.shelve.services.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingServiceImpl implements FirebaseMessagingService {

    @Autowired
    private FirebaseMessaging firebaseMessaging;

//
//    @Override
//    public String sendNotification(NoteRequest noteRequest) {
//        return null;
//    }
}
