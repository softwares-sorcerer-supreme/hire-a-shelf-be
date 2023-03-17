package com.example.shelve.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreCategoryRequest {
    private List<Long> categoriesId;
    private Long storeId;
}
