package com.example.shelve.services;

import com.example.shelve.dto.request.ShelvesRequest;
import com.example.shelve.dto.response.ShelvesResponse;

public interface ShelvesService {
    ShelvesResponse getShelve(Long id);

    ShelvesResponse createShelve(ShelvesRequest shelvesRequest);

    ShelvesResponse updateShelve(Long id, ShelvesRequest shelvesRequest);
}
