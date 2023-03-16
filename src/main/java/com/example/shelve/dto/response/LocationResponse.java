package com.example.shelve.dto.response;

import com.example.shelve.entities.Store;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponse {

    private long id;
    private String district;
    private String ward;
    private String address;
    private String city;
//    private Set<Store> store;
}
