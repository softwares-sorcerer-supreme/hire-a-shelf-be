package com.example.shelve.mapper;

import com.example.shelve.dto.request.RegistrationRequest;
import com.example.shelve.dto.response.RegistrationResponse;
import com.example.shelve.entities.Registration;
import com.example.shelve.entities.enums.Status;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

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

    public RegistrationResponse toRegistrationResponse(Registration registration) {
        return RegistrationResponse.builder()
                .id(registration.getId())
                .address(registration.getAddress())
                .note(registration.getNote())
                .phone(registration.getPhone())
                .createDate(registration.getCreateDate())
                .email(registration.getEmail())
                .typeAccount(registration.getTypeAccount())
                .status(registration.getStatus().getName())
                .name(registration.getName())
                .build();
    }

}
