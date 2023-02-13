package com.example.shelve.services.impl;

import com.example.shelve.config.CustomeUserDetail;
import com.example.shelve.config.JwtService;
import com.example.shelve.dto.request.AccountRequest;
import com.example.shelve.dto.request.RegistrationRequest;
import com.example.shelve.dto.response.AuthenticationResponse;
import com.example.shelve.dto.response.SuccessResponse;
import com.example.shelve.entities.Account;
import com.example.shelve.entities.Registration;
import com.example.shelve.exception.UserExistedException;
import com.example.shelve.mapper.RegistrationMapper;
import com.example.shelve.repository.AccountRepository;
import com.example.shelve.repository.RegistrationRepository;
import com.example.shelve.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private RegistrationMapper registrationMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse authenticationResponse(AccountRequest accountRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        accountRequest.getUserName(),
                        accountRequest.getPassword()
                ));
        var user = accountRepository.findByUserName(accountRequest.getUserName())
                .orElseThrow();

        var userDetail = new CustomeUserDetail(user);

        var jwtToken = jwtService.generateToken(userDetail);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public SuccessResponse register(RegistrationRequest registrationRequest) {
        Optional<Account> accountUserName = accountRepository.findByUserName(registrationRequest.getName());
        if(accountUserName.isPresent())
            throw new UserExistedException("This user name has been existed");

        Optional<Account> accountEmail = accountRepository.findByEmail(registrationRequest.getEmail());
        if(accountEmail.isPresent())
            throw new UserExistedException("This email has been existed");

        registrationRepository.save(registrationMapper.toRegistration(registrationRequest));

        return new SuccessResponse(HttpStatus.OK.value(), "Register account successfully!");
    }


}
