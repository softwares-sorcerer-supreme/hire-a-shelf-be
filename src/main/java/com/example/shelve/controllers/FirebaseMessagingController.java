package com.example.shelve.controllers;

import com.example.shelve.dto.request.PushNotificationToMultipleDevicesRequest;
import com.example.shelve.dto.request.PushNotificationRequest;
import com.example.shelve.dto.response.PushNotificationResponse;
import com.example.shelve.services.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sendNotification")
public class FirebaseMessagingController {

    @Autowired
    private FirebaseMessagingService firebaseMessagingService;

    @PostMapping
    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request){
        firebaseMessagingService.sendNotificationToToken(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(@RequestBody PushNotificationToMultipleDevicesRequest notificationRequest) {
        try {
            firebaseMessagingService.sendNotifications(notificationRequest.getTitle(), notificationRequest.getBody(), notificationRequest.getTokens());
            return ResponseEntity.ok().body("Message has been sent to multiple devices");
        } catch (FirebaseMessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send notification: " + e.getMessage());
        }
    }
}
