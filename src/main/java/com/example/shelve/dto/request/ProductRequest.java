package com.example.shelve.dto.request;

import com.example.shelve.entities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private long id;
    private boolean status;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private String imgURL;
    private Long categoryId;
    private Long brandId;
}
