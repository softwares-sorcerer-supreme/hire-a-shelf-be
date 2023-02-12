package com.example.shelve.dto.response;

import com.example.shelve.entities.*;

import java.sql.Date;
import java.util.Set;

public class RetailerResponse {

    private Long id;

    private String name;

    private String address;

    private String phone;

    private String email;

    private String logo;

    private String description;

    private Date participateDate;

    private boolean status;

    private Set<Store> store;
}
