package com.example.shelve.dto.request;

import com.example.shelve.entities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private boolean status;
    @NotBlank(message = "Name can not empty!")
    private String name;

    @NotBlank(message = "Description can not empty!")
    private String description;

    @NotNull(message = "Quantity can not empty!")
    @Min(value = 1, message = "Price is bigger than 0")
    private int quantity;

    @NotNull(message = "Price can not empty!")
    @Min(value = 1, message = "Price is bigger than 0")
    private BigDecimal price;

    @NotBlank(message = "Image can not empty!")
    private String imgURL;

    @NotNull(message = "Category can not empty!")
    private Long categoryId;

    @NotNull(message = "Brand can not empty!")
    private Long brandId;
}
