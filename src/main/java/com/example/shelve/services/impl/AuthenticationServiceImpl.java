package com.example.shelve.services.impl;

import com.example.shelve.config.CustomeUserDetail;
import com.example.shelve.config.JwtService;
import com.example.shelve.dto.request.AccountRequest;
import com.example.shelve.dto.request.LogoutRequest;
import com.example.shelve.dto.response.AuthenticationResponse;
import com.example.shelve.entities.Account;
import com.example.shelve.entities.FirebaseNotiToken;
import com.example.shelve.entities.Registration;
import com.example.shelve.entities.enums.EStatus;
import com.example.shelve.exception.BadRequestException;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.AccountMapper;
import com.example.shelve.repository.AccountRepository;
import com.example.shelve.repository.FirebaseTokenRepository;
import com.example.shelve.repository.RegistrationRepository;
import com.example.shelve.services.AuthenticationService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private FirebaseTokenRepository firebaseTokenRepository;

    public AuthenticationResponse authenticationResponse(AccountRequest accountRequest) {
        var user = accountRepository.findByUserName(accountRequest.getUserName())
                .orElseThrow(() -> new ResourceNotFoundException("Username or password is invalid!"));

        if (!passwordEncoder.matches(accountRequest.getPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("Username or password is invalid!");
        }

        //set firebase token to database
        Account savedAccount = accountRepository.save(user);
        saveFirebaseTokenToDatabase(savedAccount, accountRequest.getFirebaseToken());

        //authen
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        accountRequest.getUserName(),
                        accountRequest.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);


        var userDetail = new CustomeUserDetail(user);


        var jwtToken = jwtService.generateToken(userDetail);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .account(accountMapper.toAccountResponse(user))
                .role(userDetail.getAuthorities().toString())
                .message("Successfully!")
                .expiredDate(jwtService.extractExpiredDate(jwtToken))
                .status(HttpStatus.OK.value())
                .build();
    }

    @Override
    public AuthenticationResponse authenticationGoogleResponse(String idToken, String firebaseToken) {

        // idToken comes from the client app (shown above)
        FirebaseToken decodedToken = null;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
        String userName = decodedToken.getEmail();

        //check if email is registered
        Optional<Registration> registrationAccount = registrationRepository.findByEmail(userName);
        if (registrationAccount.isPresent()) {
            //check if email is existed in user
            Optional<Account> foundAccount = accountRepository.findByEmail(userName);

            if (foundAccount.isPresent()){
                if(!foundAccount.get().getUserName().equals(foundAccount.get().getEmail())){
                    throw new BadRequestException("This email has been used!");
                }
            }

            switch (registrationAccount.get().getEStatus()) {
                case PENDING:
                    throw new BadRequestException("This email is waiting for approved!");

                case DECLINED:
                    throw new BadRequestException("Your account don't have enough condition to log in!");
            }
        }

        //check if email is exist
        Optional<Account> foundAccount = accountRepository.findByUserName(userName);
        if (foundAccount.isEmpty()) {
            //Write code to response that user is the first time access the system.
            throw new ResourceNotFoundException("User not found!");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        foundAccount.get().getUserName(),
                        "123456"
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);


        //set firebase token
        Account savedAccount = accountRepository.save(foundAccount.get());
        saveFirebaseTokenToDatabase(savedAccount, firebaseToken);

        var userDetail = new CustomeUserDetail(foundAccount.get());
        var jwtToken = jwtService.generateToken(userDetail);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .account(accountMapper.toAccountResponse(foundAccount.get()))
                .role(userDetail.getAuthorities().toString())
                .expiredDate(jwtService.extractExpiredDate(jwtToken))
                .message("Successfully!")
                .status(HttpStatus.OK.value())
                .build();
    }

    @Override
    public AuthenticationResponse logout(LogoutRequest logoutRequest) {
        FirebaseNotiToken firebaseNotiToken = firebaseTokenRepository.findByTokenAndAccountId(logoutRequest.getFirebaseToken(), logoutRequest.getAccountId());
        if (firebaseNotiToken!=null){
            firebaseNotiToken.setStatus(false);
            firebaseTokenRepository.save(firebaseNotiToken);
            return null;
        }
        return null;
    }

    public void saveFirebaseTokenToDatabase(Account savedAccount, String firebaseToken){
        FirebaseNotiToken notiToken = firebaseTokenRepository.findByTokenAndAccountId(firebaseToken, savedAccount.getId());
        if(notiToken == null){
            FirebaseNotiToken firebaseNotiToken = FirebaseNotiToken.builder()
                    .token(firebaseToken)
                    .account(savedAccount)
                    .status(true)
                    .build();
            firebaseTokenRepository.save(firebaseNotiToken);
        }else{
            if(!notiToken.isStatus()){
                notiToken.setStatus(true);
                firebaseTokenRepository.save(notiToken);
            }
        }
    }
}

