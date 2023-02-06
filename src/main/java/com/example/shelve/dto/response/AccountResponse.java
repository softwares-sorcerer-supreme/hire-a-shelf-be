package com.example.shelve.dto.response;

import com.example.shelve.entities.Admin;
import com.example.shelve.entities.Brand;
import com.example.shelve.entities.Retailer;

public class AccountResponse {

    private long id;

    private String username;

    private String role;

    private boolean status;

    private Admin admin;

    private Retailer retailer;

    private Brand brand;
}
