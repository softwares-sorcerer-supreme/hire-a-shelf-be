package com.example.shelve.mapper;

import com.example.shelve.dto.response.AccountResponse;
import com.example.shelve.entities.Account;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private BrandMapper brandMapper;

    public AccountResponse toAccountResponse (Account account){
        AccountResponse accountResponse = AccountResponse.builder()
                .id(account.getId())
                .username(account.getUserName())
                .email(account.getEmail())
                .status(account.isStatus())
                .email(account.getEmail())
                .admin(adminMapper.toAdminResponse(account.getAdmin()))
                .store(storeMapper.toStoreResponse(account.getStore()))
                .brand(brandMapper.toBrandResponse(account.getBrand()))
                .build();

        return accountResponse;
    }
}
