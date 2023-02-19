package com.example.shelve.controllers;

import com.example.shelve.services.FirebaseMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private FirebaseMessagingService firebaseMessagingService;
//
//    @PostMapping
//    public String sendNotification(
//            @RequestBody NoteRequest note) {
//        return firebaseMessagingService.sendNotification(note);
//    }

}
