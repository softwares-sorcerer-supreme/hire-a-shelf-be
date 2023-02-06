package com.example.shelve.dto.response;

import com.example.shelve.entities.Account;
import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.Products;

import java.util.Set;

public class BrandResponse {

    private Long id;

    private String name;

    private String address;

    private String phone;

    private String email;

    private String logo;

    private String description;

    private boolean status;

    private Set<Campaign> campaigns;

    private Set<Products> products;

    private Account account;
}
