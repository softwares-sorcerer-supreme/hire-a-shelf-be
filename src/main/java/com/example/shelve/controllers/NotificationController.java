package com.example.shelve.controllers;

import com.example.shelve.dto.response.BrandResponse;
import com.example.shelve.dto.response.NotificationResponse;
import com.example.shelve.services.BrandService;
import com.example.shelve.services.FirebaseMessagingService;
import com.example.shelve.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/{id}")
    public ResponseEntity<List<NotificationResponse>> getNotificationByAccountId(@PathVariable  Long id) {
        return new ResponseEntity<>(notificationService.getNotificationByAccountId(id), HttpStatus.OK);
    }

}
