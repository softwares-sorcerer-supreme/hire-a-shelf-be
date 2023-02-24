package com.example.shelve.dto.response;

import com.example.shelve.entities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

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
//    private CategoryResponse category;

}
