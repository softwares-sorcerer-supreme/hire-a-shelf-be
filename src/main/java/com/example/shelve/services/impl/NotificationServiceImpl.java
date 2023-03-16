package com.example.shelve.services.impl;

import com.example.shelve.dto.response.CampaignProductResponse;
import com.example.shelve.dto.response.NotificationResponse;
import com.example.shelve.entities.Account;
import com.example.shelve.entities.Notification;
import com.example.shelve.mapper.NotificationMapper;
import com.example.shelve.repository.AccountRepository;
import com.example.shelve.repository.NotificationRepository;
import com.example.shelve.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    NotificationMapper mapper;

    @Override
    public List<NotificationResponse> getNotificationByAccountId(Long id) {
        List<NotificationResponse> notificationResponses = new ArrayList<>();
        notificationRepository.getNotificationByAccount_Id(id)
                .forEach(x -> notificationResponses.add(mapper.toNotificationResponse(x)));
        return notificationResponses;
    }

    public NotificationResponse addANotification(String title, String body, Long accountId){
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isEmpty()){
            throw new NotFoundException("Account not found!");
        }else{
            Notification notification = Notification.builder()
                    .account(accountOptional.get())
                    .title(title)
                    .body(body)
                    .build();
            return mapper.toNotificationResponse(notificationRepository.save(notification)) ;
        }
    }

    public NotificationResponse addNotificationByStore(String title, String body, Long storeId){
        Optional<Account> accountOptional = accountRepository.findByStoreId(storeId);
        if (accountOptional.isEmpty()){
            throw new NotFoundException("Account not found!");
        }else{
            Notification notification = Notification.builder()
                    .account(accountOptional.get())
                    .title(title)
                    .body(body)
                    .build();
            return mapper.toNotificationResponse(notificationRepository.save(notification)) ;
        }
    }

    public NotificationResponse addNotificationByBrand(String title, String body, Long brandId){
        Optional<Account> accountOptional = accountRepository.findByBrandId(brandId);
        if (accountOptional.isEmpty()){
            throw new NotFoundException("Account not found!");
        }else{
            Notification notification = Notification.builder()
                    .account(accountOptional.get())
                    .title(title)
                    .body(body)
                    .build();
            return mapper.toNotificationResponse(notificationRepository.save(notification)) ;

        }
    }
}
