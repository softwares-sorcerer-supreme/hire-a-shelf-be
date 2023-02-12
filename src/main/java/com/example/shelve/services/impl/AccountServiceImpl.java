package com.example.shelve.services.impl;

import com.example.shelve.dto.response.AccountResponse;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.AccountMapper;
import com.example.shelve.repository.AccountRepository;
import com.example.shelve.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper mapper;

    @Override
    public List<AccountResponse> getAllAccount() {
        List<AccountResponse> accountResponses = new ArrayList<>();
        accountRepository.findAll().forEach(x-> accountResponses.add(mapper.toAccountResponse(x)));
        return accountResponses;
    }

    @Override
    public AccountResponse getAccount(Long id) {
        AccountResponse accountResponse = mapper.toAccountResponse(accountRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found")));
        return accountResponse;
    }
}
