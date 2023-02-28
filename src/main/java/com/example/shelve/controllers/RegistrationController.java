package com.example.shelve.controllers;

import com.example.shelve.dto.request.RegistrationRequest;
import com.example.shelve.dto.response.RegistrationResponse;
import com.example.shelve.dto.response.SuccessResponse;
import com.example.shelve.entities.enums.EStatus;
import com.example.shelve.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public ResponseEntity<List<RegistrationResponse>> getRegistration() {
        return new ResponseEntity<>(registrationService.getAllRegistration(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationResponse> getRegistrationById(@PathVariable Long id) {
        return new ResponseEntity<>(registrationService.getRegistrationById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> registration(@RequestBody RegistrationRequest registration) {
        return new ResponseEntity<>(registrationService.register(registration), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> approvalRegistration(@RequestParam(name = "status") EStatus status,
                                                                     @PathVariable Long id) {
        return new ResponseEntity<>(registrationService.approve(status, id), HttpStatus.OK);
    }

}
