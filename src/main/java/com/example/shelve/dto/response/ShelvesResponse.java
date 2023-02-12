package com.example.shelve.dto.response;

import com.example.shelve.entities.*;

import java.util.Set;

public class ShelvesResponse {

    private long id;

    private String name;

    private String description;

    private boolean status;

    private Store store;

    private ShelvesType shelvesType;

    private Set<Image> images;

    private Set<Campaign>  campaigns;

    private Set<Products> products;
}
