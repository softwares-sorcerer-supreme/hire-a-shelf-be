package com.example.shelve.dto.request;

import com.example.shelve.entities.Image;
import com.example.shelve.entities.ShelveType;
import com.example.shelve.entities.ShelvesProducts;
import com.example.shelve.entities.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShelvesRequest {

    private String name;
    private String description;
    private boolean status;
    private Long storeId;
    private Long shelvesTypeId;
//    private Set<Long> shelvesProducts;
//    private Set<Image> images;
}
