package com.example.shelve.dto.response;

import com.example.shelve.entities.Shelve;

import java.util.Set;

public class ShelvesTypeResponse {

    private long id;

    private String name;

    private String description;

    private boolean status;

    private Set<Shelve> shelves;

}
