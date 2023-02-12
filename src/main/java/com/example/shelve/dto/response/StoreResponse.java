package com.example.shelve.dto.response;

import com.example.shelve.entities.*;

import java.sql.Date;
import java.util.Set;

public class StoreResponse {

    private Long id;

    private String name;

    private String phone;

    private String email;

    private String logo;

    private String description;

    private Date participateDate;

    private boolean status;

    private Set<Contract> contracts;

    private Set<Shelves> shelves;

    private Set<Order> orders;

    private Account account;

    private Location location;

    private Retailer retailer;

    private Set<Category>  categories;
}
