package com.example.shelve.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationResponse {

    private long id;
    private String email;
    private String phone;
    private String name;
    private String note;
    private Date createDate;
    private String typeAccount;
    private String status;
    private LocationResponse locationResponse;
}
