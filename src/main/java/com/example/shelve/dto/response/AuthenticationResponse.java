package com.example.shelve.dto.response;

import com.example.shelve.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private Date expiredDate;
    private AccountResponse account;
    private String message;
    private int status;
}
