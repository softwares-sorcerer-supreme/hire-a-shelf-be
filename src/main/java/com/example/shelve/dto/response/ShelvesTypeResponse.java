package com.example.shelve.dto.response;

import com.example.shelve.entities.Shelves;

import java.util.Set;

public class ShelvesTypeResponse {

    private long id;

    private String name;

    private String description;

    private boolean status;

    private Set<Shelves> shelves;

}
