package com.example.shelve.services;

import com.example.shelve.dto.request.RegistrationRequest;
import com.example.shelve.dto.response.RegistrationResponse;
import com.example.shelve.dto.response.SuccessResponse;

import java.util.List;

public interface RegistrationService {
    SuccessResponse register(RegistrationRequest registration);

    List<RegistrationResponse> getAllRegistration();

    RegistrationResponse approve(RegistrationRequest registration);
}
