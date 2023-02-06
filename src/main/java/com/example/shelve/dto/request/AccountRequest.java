package com.example.shelve.dto.request;

import com.example.shelve.entities.Admin;
import com.example.shelve.entities.Brand;
import com.example.shelve.entities.Retailer;

public class AccountRequest {

    private long id;
    private String username;
    private String password;
    private String role;
    private boolean status;
    private Admin admin;
    private Retailer retailer;
    private Brand brand;
}
