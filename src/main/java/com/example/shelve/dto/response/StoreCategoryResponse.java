package com.example.shelve.dto.response;

import lombok.*;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreCategoryResponse {
    private Long id;
    private CategoryResponse categoryResponse;
    private StoreResponse storeResponse;
}
