package com.example.shelve.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordRequest {
    private String username;
    private String email;
    private String phone;
    private String oldPassword;
    private String newPassword;
}
