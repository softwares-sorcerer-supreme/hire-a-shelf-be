package com.example.shelve.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private Date expiredDate;
    private String userName;
    private String name;
    private String imgUrl;
    private String message;
    private int status;
}
