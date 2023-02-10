package com.example.shelve.dto.response;

import com.example.shelve.entities.Store;

import java.util.Set;

public class LocationResponse {

    private long id;

    private String district;

    private String address;

    private String city;

    private Set<Store> store;
}
