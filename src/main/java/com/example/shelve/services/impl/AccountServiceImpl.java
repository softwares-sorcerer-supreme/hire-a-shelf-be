package com.example.shelve.services.impl;

import com.example.shelve.repository.AccountRepository;
import com.example.shelve.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
}
