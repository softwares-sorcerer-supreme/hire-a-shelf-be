package com.example.shelve.dto.request;

import com.example.shelve.entities.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    private String email;
    private String phone;
    private String name;
    private String note;
    private String typeAccount;
    private boolean isRegisterByGoogle;
    private EStatus EStatus;
    private LocationRequest location;

}
