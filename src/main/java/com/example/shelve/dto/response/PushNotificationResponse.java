package com.example.shelve.dto.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PushNotificationResponse {

    private int status;
    private String message;
}
