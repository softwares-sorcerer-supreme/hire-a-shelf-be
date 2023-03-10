package com.example.shelve.controllers;

import com.example.shelve.dto.request.DataMailRequest;
import com.example.shelve.services.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sendmail")
@RequiredArgsConstructor
public class SendMailController {

    @Autowired
    private MailService mailService;

    @PostMapping
    public ResponseEntity<String> sendMail(@RequestBody  DataMailRequest dataMailRequest){
        mailService.sendMailApprovedAccount(dataMailRequest);
        return new ResponseEntity<>("Send Email", HttpStatus.OK);
    }
}
