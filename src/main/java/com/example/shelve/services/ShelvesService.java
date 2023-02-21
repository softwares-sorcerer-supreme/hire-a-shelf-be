package com.example.shelve.services;

import com.example.shelve.dto.response.ShelvesResponse;

public interface ShelvesService {
    ShelvesResponse getShelve(Long id);
}
