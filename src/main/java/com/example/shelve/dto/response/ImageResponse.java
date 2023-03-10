package com.example.shelve.dto.response;

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
