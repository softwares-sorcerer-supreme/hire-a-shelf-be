package com.example.shelve.mapper;

import com.example.shelve.dto.response.ShelvesTypeResponse;
import com.example.shelve.entities.ShelvesType;
import org.springframework.stereotype.Component;

@Component
public class ShelvesTypeMapper {
    public ShelvesTypeResponse toShelvesTypeResponse (ShelvesType shelvesType){
        return ShelvesTypeResponse.builder()
                .name(shelvesType.getName())
                .id(shelvesType.getId())
                .description(shelvesType.getDescription())
                .status(shelvesType.isStatus())
                .build();
    }
}
