package com.example.shelve.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse implements Serializable {


    private long id;
    private boolean status;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private String imgURL;
    private CategoryResponse categoryResponse;

}
