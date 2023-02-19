package com.example.shelve.mapper;

import com.example.shelve.dto.response.StoreResponse;
import com.example.shelve.entities.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {

    @Autowired
    private LocationMapper locationMapper;

    public StoreResponse toStoreResponse(Store store) {
        if(store == null)
            return null;

        return StoreResponse.builder()
                .name(store.getName())
                .phone(store.getPhone())
                .logo(store.getLogo())
                .description(store.getDescription())
                .participateDate(store.getParticipateDate())
                .status(store.isStatus())
                .location(locationMapper.toLocationResponse(store.getLocation()))
                .build();
    }

}
