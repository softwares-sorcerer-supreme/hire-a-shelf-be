package com.example.shelve.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    private String email;
    private String address;
    private String phone;
    private String name;
    private String note;
    private String typeAccount;

}
