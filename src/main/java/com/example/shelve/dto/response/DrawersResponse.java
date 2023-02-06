package com.example.shelve.dto.response;

import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.ProductDrawersDetails;
import com.example.shelve.entities.Shelves;

import java.util.Set;

public class DrawersResponse {

    private long id;

    private String name;

    private String description;

    private boolean status;

    private Shelves shelves;

    private Set<Campaign> campaigns;

    private Set<ProductDrawersDetails> productDrawersDetails;
}
