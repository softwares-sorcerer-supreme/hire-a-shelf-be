package com.example.shelve.dto.response;

import com.example.shelve.entities.Admin;
import com.example.shelve.entities.Brand;
import com.example.shelve.entities.Store;

public class AccountResponse {

    private long id;

    private String username;

    private String role;

    private boolean status;

    private Admin admin;

    private Store store;

    private Brand brand;
}
