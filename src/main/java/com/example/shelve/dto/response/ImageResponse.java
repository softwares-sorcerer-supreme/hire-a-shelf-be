package com.example.shelve.dto.response;

import com.example.shelve.entities.Product;
import com.example.shelve.entities.Shelve;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {

    private long id;

    private String imgUrl;
}
