package com.example.shelve.dto.response;

import com.example.shelve.entities.Account;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class NotificationResponse {
    private String title;
    private String body;
    private AccountResponse accountResponse;
}
