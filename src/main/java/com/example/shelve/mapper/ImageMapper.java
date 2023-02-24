package com.example.shelve.mapper;

import com.example.shelve.dto.response.AccountResponse;
import com.example.shelve.dto.response.ImageResponse;
import com.example.shelve.entities.Account;
import com.example.shelve.entities.Image;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder
@Component
public class ImageMapper {

    public ImageResponse toImageResponse (Image image){
        ImageResponse imageResponse = ImageResponse.builder()
                .id(image.getId())
                .imgUrl(image.getImgUrl())
                .build();
        return imageResponse;
    }
}
