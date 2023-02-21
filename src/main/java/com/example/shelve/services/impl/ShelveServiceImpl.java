package com.example.shelve.services.impl;

import com.example.shelve.dto.response.ShelvesResponse;
import com.example.shelve.entities.Shelve;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.ShelveMapper;
import com.example.shelve.repository.ShelveRepository;
import com.example.shelve.services.ShelvesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShelveServiceImpl implements ShelvesService {

    @Autowired
    private ShelveRepository shelvesRepository;
    @Autowired
    private ShelveMapper shelveMapper;

    @Override
    public ShelvesResponse getShelve(Long id) {
        Shelve shelve = shelvesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shelve not found!"));

        return shelveMapper.toShelveResponse(shelve);
    }
}
