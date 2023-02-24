package com.example.shelve.dto.request;

import com.example.shelve.entities.Account;
import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.Location;
import com.example.shelve.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandRequest {

    private String name;
    private Set<Location> locations;

    private String phone;
    private String logo;
    private String description;
    private boolean status;
}
