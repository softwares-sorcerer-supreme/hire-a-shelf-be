package com.example.shelve.dto.response;

import com.example.shelve.entities.Shelves;
import lombok.*;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShelvesTypeResponse {

    private long id;

    private String name;

    private String description;

    private boolean status;

//    private Set<Shelves> shelves;

}
