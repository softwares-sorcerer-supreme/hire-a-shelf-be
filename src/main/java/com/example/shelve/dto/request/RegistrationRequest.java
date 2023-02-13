package com.example.shelve.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

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
