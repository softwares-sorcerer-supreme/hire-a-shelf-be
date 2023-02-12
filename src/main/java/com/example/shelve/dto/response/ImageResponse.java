package com.example.shelve.dto.response;

import com.example.shelve.entities.Products;
import com.example.shelve.entities.Shelves;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {

    private long id;

    private String imgUrl;

    private Shelves shelves;
}
