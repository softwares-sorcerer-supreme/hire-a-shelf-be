package com.example.shelve.services.impl;

import ch.qos.logback.core.status.Status;
import com.example.shelve.dto.request.RegistrationRequest;
import com.example.shelve.dto.response.RegistrationResponse;
import com.example.shelve.dto.response.SuccessResponse;
import com.example.shelve.entities.*;
import com.example.shelve.entities.enums.EStatus;
import com.example.shelve.exception.BadRequestException;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.exception.UserExistedException;
import com.example.shelve.mapper.LocationMapper;
import com.example.shelve.mapper.RegistrationMapper;
import com.example.shelve.repository.*;
import com.example.shelve.services.RegistrationService;
import com.example.shelve.utils.GeneratePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

import static com.example.shelve.entities.enums.EStatus.PENDING;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private RegistrationMapper registrationMapper;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GeneratePassword generatePassword;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public SuccessResponse register(RegistrationRequest registrationRequest) {
        //Check email duplicate
        Optional<Registration> accountEmail = registrationRepository.findByEmail(registrationRequest.getEmail());
        if (accountEmail.isPresent())
            throw new UserExistedException("This email has been registered");

        //Save new location
        Location location = locationRepository.save(locationMapper.toLocation(registrationRequest.getLocation()));


        //Mapping
        Registration registration = registrationMapper.toRegistration(registrationRequest);

        //Map location to registration
        registration.setLocation(location);
        registration.setEStatus(PENDING);

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
    public RegistrationResponse getRegistrationById(Long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found!"));

        return registrationMapper.toRegistrationResponse(registration);
    }


    @Override
    public SuccessResponse approve(EStatus status, Long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found!"));

        if(registration.getEStatus() != PENDING)
            throw new BadRequestException("This registration has been " + registration.getEStatus());

        switch (status) {
            case APPROVED:
                pushNotification(true);
                handleStatusApproved(registration);
                break;

            case DECLINED:
                //push notification to notice
                pushNotification(false);
                break;

            default:
                throw new BadRequestException("Error status");
        }

        registration.setEStatus(status);
        registrationRepository.save(registration);

        return new SuccessResponse().builder()
                .message(status.getName() + " Successfully!")
                .status(HttpStatus.OK.value())
                .build();

    }

    private void handleStatusApproved(Registration registration) {
        String username = registration.getEmail().substring(0, registration.getEmail().indexOf('@'));

        Account account = Account.builder()
                .email(registration.getEmail())
                .userName(username)
                .password(passwordEncoder.encode(generatePassword.generatePassword()))
                .status(true)
                .build();

        switch (registration.getTypeAccount()) {
            case "Brand":
                Brand brand = brandRepository.save(Brand.builder()
                        .name(registration.getName())
                        .phone(registration.getPhone())
                        .locations(Collections.singleton(registration.getLocation()))
                        .name(registration.getName())
                        .participateDate(new Date(System.currentTimeMillis()))
                        .status(true)
                        .build());

                account.setBrand(brand);
                break;

            case "Store":
                Store store = Store.builder()
                        .name(registration.getName())
                        .phone(registration.getPhone())
                        .location(registration.getLocation())
                        .name(registration.getName())
                        .participateDate(new Date(System.currentTimeMillis()))
                        .status(true)
                        .build();

                account.setStore(store);
                break;
        }
        accountRepository.save(account);
    }


    private void pushNotification(boolean isApproved) {
        //notify to user's email
        if (isApproved) {
            //using firebase to send email the user's password


        }


    }


}
