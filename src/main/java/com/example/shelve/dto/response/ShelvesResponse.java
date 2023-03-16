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

    private ShelvesTypeResponse shelvesTypeResponse;

    private Set<ImageResponse> images;

    private String imgURL;

    private Set<ProductResponse> products;
}
