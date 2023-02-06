package com.example.shelve.services.impl;

import com.example.shelve.repository.RegistrationRepository;
import com.example.shelve.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;
}
