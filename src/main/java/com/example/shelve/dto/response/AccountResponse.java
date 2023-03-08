package com.example.shelve.dto.response;

import com.example.shelve.entities.Admin;
import com.example.shelve.entities.Brand;
import com.example.shelve.entities.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse implements Serializable {

    private long id;

    private String username;
    private String email;
    private boolean status;

    private AdminResponse admin;
    private StoreResponse store;
    private BrandResponse brand;
}
