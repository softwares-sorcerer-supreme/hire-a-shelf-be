package com.example.shelve.services;

import com.example.shelve.dto.request.RegistrationRequest;
import com.example.shelve.dto.response.RegistrationResponse;
import com.example.shelve.dto.response.SuccessResponse;
import com.example.shelve.entities.enums.EStatus;

import java.util.List;

public interface RegistrationService {
    SuccessResponse register(RegistrationRequest registration);

    List<RegistrationResponse> getAllRegistration();

    SuccessResponse approve(EStatus status, Long id);

    RegistrationResponse getRegistrationById(Long id);

    RegistrationResponse regisAdminAccount(String secretKey);
}
