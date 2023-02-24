package com.example.shelve.services.impl;

import com.example.shelve.dto.response.CampaignProductResponse;
import com.example.shelve.dto.response.NotificationResponse;
import com.example.shelve.mapper.NotificationMapper;
import com.example.shelve.repository.NotificationRepository;
import com.example.shelve.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationMapper mapper;

    @Override
    public List<NotificationResponse> getNotificationByAccountId(Long id) {
        List<NotificationResponse> notificationResponses = new ArrayList<>();
        notificationRepository.getNotificationByAccount_Id(id)
                .forEach(x -> notificationResponses.add(mapper.toNotificationResponse(x)));
        return notificationResponses;
    }
}
