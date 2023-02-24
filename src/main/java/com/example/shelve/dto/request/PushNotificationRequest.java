package com.example.shelve.dto.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PushNotificationRequest {

    private String title;
    private String message;
    private String topic;
    private String token;

}
