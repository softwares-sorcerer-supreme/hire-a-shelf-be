package com.example.shelve.services.impl;

import com.example.shelve.config.CustomeUserDetail;
import com.example.shelve.config.JwtService;
import com.example.shelve.dto.request.AccountRequest;
import com.example.shelve.dto.request.RegistrationRequest;
import com.example.shelve.dto.response.AuthenticationResponse;
import com.example.shelve.dto.response.SuccessResponse;
import com.example.shelve.entities.Account;
import com.example.shelve.entities.Registration;
import com.example.shelve.entities.enums.Status;
import com.example.shelve.exception.ErrorResponse;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.exception.UserExistedException;
import com.example.shelve.mapper.RegistrationMapper;
import com.example.shelve.repository.AccountRepository;
import com.example.shelve.repository.RegistrationRepository;
import com.example.shelve.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse authenticationResponse(AccountRequest accountRequest) {
        var user = accountRepository.findByUserName(accountRequest.getUserName())
                .orElseThrow(() -> new ResourceNotFoundException("User name has not found"));

        if(!passwordEncoder.matches(accountRequest.getPassword(), user.getPassword())) {
            return AuthenticationResponse.builder()
                    .message("Invalid Password!")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
        }


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        accountRequest.getUserName(),
                        accountRequest.getPassword()
                ));


        var userDetail = new CustomeUserDetail(user);

        var jwtToken = jwtService.generateToken(userDetail);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .message("Successfully!")
                .status(HttpStatus.OK.value())
                .build();
    }




}
