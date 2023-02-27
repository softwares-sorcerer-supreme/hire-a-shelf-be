package com.example.shelve.dto.response;

import com.example.shelve.entities.Account;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    private String title;
    private String body;
    private AccountResponse account;
}
