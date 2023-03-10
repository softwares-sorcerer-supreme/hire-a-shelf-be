package com.example.shelve.services;

import com.example.shelve.dto.request.ShelvesRequest;
import com.example.shelve.dto.response.APIResponse;
import com.example.shelve.dto.response.ShelvesResponse;

import java.util.List;

public interface ShelvesService {
    ShelvesResponse getShelve(Long id);

    ShelvesResponse createShelve(ShelvesRequest shelvesRequest);

    ShelvesResponse updateShelve(Long id, ShelvesRequest shelvesRequest);

    APIResponse<List<ShelvesResponse>> getListShelvesWithFilter(long storeId, String keyword, int page, String status);
}
