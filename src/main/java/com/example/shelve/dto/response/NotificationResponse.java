package com.example.shelve.dto.response;

import com.example.shelve.entities.Account;
import com.example.shelve.entities.enums.ENotificationType;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    private String title;
    private String body;
    private AccountResponse accountResponse;
    private String type;
}
