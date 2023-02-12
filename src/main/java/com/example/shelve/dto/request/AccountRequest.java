package com.example.shelve.dto.request;

import com.example.shelve.entities.Admin;
import com.example.shelve.entities.Brand;
import com.example.shelve.entities.Store;

public class AccountRequest {

    private String username;
    private String password;
    private boolean status;
    private Admin admin;
    private Store store;
    private Brand brand;
}

