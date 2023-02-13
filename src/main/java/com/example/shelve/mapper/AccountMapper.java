package com.example.shelve.mapper;

import com.example.shelve.dto.response.AccountResponse;
import com.example.shelve.entities.Account;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponse toAccountResponse (Account account){
        AccountResponse accountResponse = AccountResponse.builder()
                .id(account.getId())
                .username(account.getUserName())
                .status(account.isStatus())
                .admin(account.getAdmin())
                .store(account.getStore())
                .brand(account.getBrand())
                .build();

        return accountResponse;
    }
}
