package com.example.shelve.services;

import com.example.shelve.dto.request.AccountRequest;
import com.example.shelve.dto.request.LogoutRequest;
import com.example.shelve.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticationResponse(AccountRequest accountRequest);

    AuthenticationResponse authenticationGoogleResponse(String idToken, String firebaseToken);

    AuthenticationResponse logout(LogoutRequest logoutRequest);
}
