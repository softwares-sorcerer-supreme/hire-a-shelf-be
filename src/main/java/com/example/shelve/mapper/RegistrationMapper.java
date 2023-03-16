package com.example.shelve.mapper;

import com.example.shelve.dto.request.RegistrationRequest;
import com.example.shelve.dto.response.RegistrationResponse;
import com.example.shelve.entities.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class RegistrationMapper {

    @Autowired
    private LocationMapper locationMapper;

    public Registration toRegistration(RegistrationRequest registrationRequest) {
        return Registration.builder()
                .note(registrationRequest.getNote())
                .phone(registrationRequest.getPhone())
                .email(registrationRequest.getEmail())
                .name(registrationRequest.getName())
                .typeAccount(registrationRequest.getTypeAccount())
                .createDate(new Date(System.currentTimeMillis()))
                .location(locationMapper.toLocation(registrationRequest.getLocation()))
                .isRegisterByGoogle(registrationRequest.isRegisterByGoogle())
                .build();
    }

    public RegistrationResponse toRegistrationResponse(Registration registration) {
        return RegistrationResponse.builder()
                .id(registration.getId())
                .note(registration.getNote())
                .phone(registration.getPhone())
                .createDate(registration.getCreateDate())
                .email(registration.getEmail())
                .typeAccount(registration.getTypeAccount())
                .status(registration.getEStatus().getName())
                .name(registration.getName())
                .locationResponse(locationMapper.toLocationResponse(registration.getLocation()))
                .build();
    }

}
