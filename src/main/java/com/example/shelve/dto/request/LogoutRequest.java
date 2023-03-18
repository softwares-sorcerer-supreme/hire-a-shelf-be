package com.example.shelve.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LogoutRequest {
    private Long accountId;
    private String firebaseToken;
}
