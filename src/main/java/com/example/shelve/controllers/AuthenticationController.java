package com.example.shelve.controllers;

import com.example.shelve.dto.request.AccountRequest;
import com.example.shelve.dto.response.AuthenticationResponse;
import com.example.shelve.entities.Registration;
import com.example.shelve.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(
//            @RequestBody RegistrationRequest request
//            ) {
//
//    }

    @PostMapping()
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AccountRequest request
    ) {
        return new ResponseEntity<>(authenticationService.authenticationResponse(request), HttpStatus.OK);
    }

    @PostMapping("/google")
    public ResponseEntity<AuthenticationResponse> googleAuthenticate(
            @RequestParam String idToken,
            @RequestParam String firebaseToken
    ) {
        return new ResponseEntity<>(authenticationService.authenticationGoogleResponse(idToken, firebaseToken), HttpStatus.OK);
    }

}
