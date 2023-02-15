package com.example.shelve.services.impl;

import com.example.shelve.config.CustomeUserDetail;
import com.example.shelve.config.JwtService;
import com.example.shelve.dto.request.AccountRequest;
import com.example.shelve.dto.response.AuthenticationResponse;
import com.example.shelve.entities.Account;
import com.example.shelve.repository.AccountRepository;
import com.example.shelve.services.AuthenticationService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
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
    public AuthenticationResponse authenticationGoogleResponse(String idToken) {
        String filePath = "config/serviceAccountKey.json";
        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream(filePath)))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FirebaseApp firebaseApp = null;
        List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
        for (FirebaseApp app : firebaseApps) {
            if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                firebaseApp = app;
                break;
            }
        }
        if (firebaseApp == null) {
            firebaseApp = FirebaseApp.initializeApp(options);
        }

        // idToken comes from the client app (shown above)\
        FirebaseToken decodedToken = null;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
        String userName = decodedToken.getEmail();
        Optional<Account> foundAccount = accountRepository.findByUserName(userName);
        if (foundAccount.isEmpty()){
            //Write code to response that user is the first time access the system.
            return AuthenticationResponse.builder().token("User not in the system!").build();

        }else{
            var userDetail = new CustomeUserDetail(foundAccount.get());

            var jwtToken = jwtService.generateToken(userDetail);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
    }
}
