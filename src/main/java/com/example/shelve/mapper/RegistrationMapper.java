package com.example.shelve.mapper;

import com.example.shelve.dto.request.RegistrationRequest;
import com.example.shelve.entities.Registration;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class RegistrationMapper {

    public Registration toRegistration(RegistrationRequest registrationRequest) {
        return Registration.builder()
                .address(registrationRequest.getAddress())
                .note(registrationRequest.getNote())
                .phone(registrationRequest.getPhone())
                .email(registrationRequest.getEmail())
                .name(registrationRequest.getName())
                .typeAccount(registrationRequest.getTypeAccount())
                .createDate(new Date(System.currentTimeMillis()))
                .build();
    }

}
