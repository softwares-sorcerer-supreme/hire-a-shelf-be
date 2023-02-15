package com.example.shelve.services.impl;

import com.example.shelve.dto.request.RegistrationRequest;
import com.example.shelve.dto.response.RegistrationResponse;
import com.example.shelve.dto.response.SuccessResponse;
import com.example.shelve.entities.Account;
import com.example.shelve.entities.Registration;
import com.example.shelve.entities.enums.Status;
import com.example.shelve.exception.UserExistedException;
import com.example.shelve.mapper.RegistrationMapper;
import com.example.shelve.repository.AccountRepository;
import com.example.shelve.repository.RegistrationRepository;
import com.example.shelve.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private RegistrationMapper registrationMapper;

    @Override
    public SuccessResponse register(RegistrationRequest registrationRequest) {
        //Check email duplicate
        Optional<Registration> accountEmail = registrationRepository.findByEmail(registrationRequest.getEmail());
        if(accountEmail.isPresent())
            throw new UserExistedException("This email has been existed");

        Registration registration = registrationMapper.toRegistration(registrationRequest);

        registration.setStatus(Status.PENDING);
        registrationRepository.save(registration);

        return new SuccessResponse(HttpStatus.OK.value(), "Register account successfully!");
    }

    @Override
    public List<RegistrationResponse> getAllRegistration() {
        List<RegistrationResponse> responseList = new ArrayList<>();
        registrationRepository.findAll().stream().forEach(registration -> responseList.add(registrationMapper.toRegistrationResponse(registration)));
        return responseList;
    }

    @Override
    public RegistrationResponse approve(RegistrationRequest registrationRequest) {
//        Registration registration = registrationMapper.toRegistration(registrationRepository.);
        return null;
    }


}
