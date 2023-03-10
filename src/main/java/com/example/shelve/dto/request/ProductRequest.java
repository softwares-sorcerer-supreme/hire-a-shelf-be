package com.example.shelve.dto.request;

import com.example.shelve.entities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private boolean status;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private MultipartFile imgMultipart;
    private Long categoryId;
    private Long brandId;
}
