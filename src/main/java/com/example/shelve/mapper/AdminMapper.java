package com.example.shelve.mapper;

import com.example.shelve.dto.response.AccountResponse;
import com.example.shelve.dto.response.AdminResponse;
import com.example.shelve.entities.Account;
import com.example.shelve.entities.Admin;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class AdminMapper {

    public AdminResponse toAdminResponse (Admin admin){
        AdminResponse adminResponse = AdminResponse.builder()
                .id(admin.getId())
                .address(admin.getAddress())
                .email(admin.getEmail())
                .phone(admin.getPhone())
                .build();
        return adminResponse;
    }

}
