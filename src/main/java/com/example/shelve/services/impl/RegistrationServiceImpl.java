package com.example.shelve.services.impl;

import ch.qos.logback.core.status.Status;
import com.example.shelve.dto.request.DataMailRequest;
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
import com.example.shelve.services.MailService;
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
    private MailService mailService;
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

        //In registration
        Optional<Registration> regisEmail = registrationRepository.findByEmail(registrationRequest.getEmail());
        if (regisEmail.isPresent())
            throw new UserExistedException("This email has been registered");

        //Save new location
        Location location = locationMapper.toLocation(registrationRequest.getLocation());
        location.setStatus(true);
        Location locationSaved = locationRepository.save(location);

        //Mapping
        Registration registration = registrationMapper.toRegistration(registrationRequest);

        //Map location to registration
        registration.setLocation(locationSaved);
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

        if (registration.getEStatus() != PENDING)
            throw new BadRequestException("This registration has been " + registration.getEStatus());

        switch (status) {
            case APPROVED:
                if (registration.isRegisterByGoogle())
                    handleStatusApprovedByGoogleRegistration(registration);
                else
                    handleStatusApproved(registration);
                break;

            case DECLINED:
                //push notification to notice
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
        String password = generatePassword.generatePassword();
        Account account = Account.builder()
                .email(registration.getEmail())
                .userName(username)
                .password(passwordEncoder.encode(password))
                .status(true)
                .build();

        accountRepository.save(initTypeAccount(registration, account));
        DataMailRequest dataMailRequest = DataMailRequest.builder()
                .email(registration.getEmail())
                .password(password)
                .userName(username)
                .build();
        mailService.sendMail(dataMailRequest);
    }

    private void handleStatusApprovedByGoogleRegistration(Registration registration) {
        Account account = Account.builder()
                .email(registration.getEmail())
                .userName(registration.getEmail())
                .password(passwordEncoder.encode("123456"))
                .status(true)
                .build();

        accountRepository.save(initTypeAccount(registration, account));

        DataMailRequest dataMailRequest = DataMailRequest.builder()
                .email(registration.getEmail())
                .userName("This field is empty because you login with google account!")
                .password("This field is empty because you login with google account!")
                .build();
        mailService.sendMail(dataMailRequest);
    }


    private Account initTypeAccount(Registration registration, Account account) {
        switch (registration.getTypeAccount()) {
            case "Brand":

                Brand brand = brandRepository.save(Brand.builder()
                        .name(registration.getName())
                        .phone(registration.getPhone())
                        .name(registration.getName())
                        .participateDate(new Date(System.currentTimeMillis()))
                        .status(true)
                        .location(locationRepository.save(registration.getLocation()))
                        .build());

                account.setBrand(brand);



                break;

            case "Store":
                Store store = storeRepository.save(Store.builder()
                        .name(registration.getName())
                        .phone(registration.getPhone())
                        .location(locationRepository.save(registration.getLocation()))
                        .name(registration.getName())
                        .participateDate(new Date(System.currentTimeMillis()))
                        .status(true)
                        .build());

                account.setStore(store);
                break;
        }

        return account;
    }
}
