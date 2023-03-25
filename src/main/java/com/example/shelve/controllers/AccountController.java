package com.example.shelve.controllers;

import com.example.shelve.dto.request.ChangePasswordRequest;
import com.example.shelve.dto.response.AccountResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccount() {
        return new ResponseEntity<>(accountService.getAllAccount(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getAccount(id), HttpStatus.OK);
    }
    @PostMapping("/password/change")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        return new ResponseEntity<>(accountService.changePassword(changePasswordRequest), HttpStatus.OK);
    }
    @PostMapping("/password/forget")
    public ResponseEntity<String> forgetPassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        return new ResponseEntity<>(accountService.forgetPassword(changePasswordRequest), HttpStatus.OK);
    }

}
