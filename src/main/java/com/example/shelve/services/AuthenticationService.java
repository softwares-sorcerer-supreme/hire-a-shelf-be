package com.example.shelve.services;

import com.example.shelve.dto.request.AccountRequest;
import com.example.shelve.dto.request.RegistrationRequest;
import com.example.shelve.dto.response.AuthenticationResponse;
import com.example.shelve.dto.response.SuccessResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticationResponse(AccountRequest accountRequest);

    SuccessResponse register(RegistrationRequest registration);
}
