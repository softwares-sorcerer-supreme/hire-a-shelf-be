package com.example.shelve.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    @NotBlank(message = "User can not be null!!!!!")
    private String userName;

    @NotBlank(message = "Password can not be null!!!!!")
    @Min(value = 8, message = "Password must be at least 8 characters")
    private String password;
    private String firebaseToken;
}
