package com.example.shelve.mapper;

import com.example.shelve.dto.response.ShelvesResponse;
import com.example.shelve.dto.response.StoreResponse;
import com.example.shelve.entities.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StoreMapper {

    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private ShelvesMapper shelvesMapper;
    public StoreResponse toStoreResponse(Store store) {
        if(store == null)
            return null;

        return StoreResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .phone(store.getPhone())
                .logo(store.getLogo())
                .description(store.getDescription())
                .participateDate(store.getParticipateDate())
                .status(store.isStatus())
                .location(locationMapper.toLocationResponse(store.getLocation()))
                .shelves(store.getShelves().stream()
                        .map(shelvesMapper::toShelveResponse)
                        .collect(Collectors.toSet()))
                .build();
    }


}
