package com.example.shelve.dto.response;

import com.example.shelve.entities.Account;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponse {

    private long id;

    private String email;

    private String address;

    private String phone;

    private Account account;
}
