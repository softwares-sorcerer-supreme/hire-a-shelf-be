package com.example.shelve.dto.response;

import com.example.shelve.entities.*;

import java.util.Set;

public class ProductResponse {

    private long id;

    private boolean status;

    private String name;

    private String description;

    private String quantity;

    private String price;

    private Set<Category> categories;

    private Set<OrderDetail> orderDetails;

    private Set<Image> images;

    private Brand brand;

    private Set<Shelves> shelves;

    private Set<Campaign> campaigns;
}
