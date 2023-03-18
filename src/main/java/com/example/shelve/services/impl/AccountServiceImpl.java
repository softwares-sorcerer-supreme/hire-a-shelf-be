package com.example.shelve.services.impl;

import com.example.shelve.dto.request.ChangePasswordRequest;
import com.example.shelve.dto.response.AccountResponse;
import com.example.shelve.entities.Account;
import com.example.shelve.exception.BadRequestException;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.AccountMapper;
import com.example.shelve.repository.AccountRepository;
import com.example.shelve.services.AccountService;
import com.example.shelve.services.MailService;
import com.example.shelve.utils.GeneratePassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private MailService mailService;
    @Autowired
    private GeneratePassword generatePassword;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountMapper mapper;

    @Override
//    @Cacheable(value = "account")
    public List<AccountResponse> getAllAccount() {
        List<AccountResponse> accountResponses = new ArrayList<>();
        accountRepository.findAll().forEach(x-> accountResponses.add(mapper.toAccountResponse(x)));
        return accountResponses;
    }

    @Override
//    @Cacheable(value = "account", key = "#id")
    public AccountResponse getAccount(Long id) {
        AccountResponse accountResponse = mapper.toAccountResponse(accountRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found")));
        return accountResponse;
    }

    @Override
    public String changePassword(ChangePasswordRequest changePasswordRequest) {
        Optional<Account> accountOptional = accountRepository.findByUserName(changePasswordRequest.getUsername());
        if (accountOptional.isEmpty()){
            throw new NotFoundException("Account with username " + changePasswordRequest.getUsername() + " could not be found!");
        }
        if(!passwordEncoder.matches(changePasswordRequest.getOldPassword(), accountOptional.get().getPassword())){
            throw new  BadRequestException("Old password is incorrect!");
        }else{
            accountOptional.get().setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        }
        return "Success";
    }

    @Override
    public String forgetPassword(ChangePasswordRequest changePasswordRequest) {
        Optional<Account> accountOptional = accountRepository.findByUserName(changePasswordRequest.getUsername());
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Information not match!");
        }else if (!accountOptional.get().getEmail().equals(changePasswordRequest.getEmail())){
            throw new ResourceNotFoundException("Information not match!");
        }else{
            log.error(accountOptional.get().getEmail());
            String newPassword = generatePassword.generatePassword();
            mailService.sendMailResetPassword(accountOptional.get().getEmail(), newPassword);
            accountOptional.get().setPassword(passwordEncoder.encode(newPassword));
            accountRepository.save(accountOptional.get());
        }
        return "Success";
    }

}
