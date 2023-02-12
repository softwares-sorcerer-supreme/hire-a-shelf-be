package com.example.shelve.dto.response;

import com.example.shelve.entities.Products;
import com.example.shelve.entities.Store;
import lombok.*;

import java.util.Set;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private long id;

    private boolean status;

    private String name;

    private String description;

    private Products products;

    private Set<Store> store;
}
