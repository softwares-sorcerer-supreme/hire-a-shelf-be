package com.example.shelve.dto.response;

import com.example.shelve.entities.Drawers;
import com.example.shelve.entities.Image;
import com.example.shelve.entities.Retailer;
import com.example.shelve.entities.ShelvesType;

import java.util.Set;

public class ShelvesResponse {

    private long id;

    private String name;

    private String description;

    private boolean status;

    private Retailer retailer;

    private ShelvesType shelvesType;

    private Set<Drawers> drawers;

    private Set<Image> images;
}
