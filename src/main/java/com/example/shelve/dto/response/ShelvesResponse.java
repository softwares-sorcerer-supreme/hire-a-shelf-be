package com.example.shelve.dto.response;

import com.example.shelve.entities.*;
import lombok.*;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShelvesResponse {

    private long id;

    private String name;

    private String description;

    private boolean status;

    private StoreResponse store;

    private ShelvesType shelvesType;

    private Set<ImageResponse> images;

    private Set<ProductResponse> products;
}
