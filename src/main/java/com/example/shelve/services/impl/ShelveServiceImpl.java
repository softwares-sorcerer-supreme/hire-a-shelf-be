package com.example.shelve.services.impl;

import com.example.shelve.dto.request.ShelvesRequest;
import com.example.shelve.dto.response.ShelvesResponse;
import com.example.shelve.entities.Shelve;
import com.example.shelve.entities.ShelveType;
import com.example.shelve.entities.Store;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.ShelveMapper;
import com.example.shelve.repository.ShelveRepository;
import com.example.shelve.repository.ShelvesTypeRepository;
import com.example.shelve.repository.StoreRepository;
import com.example.shelve.services.ShelvesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShelveServiceImpl implements ShelvesService {

    @Autowired
    private ShelveRepository shelvesRepository;
    @Autowired
    private ShelveMapper shelveMapper;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ShelvesTypeRepository shelvesTypeRepository;

    @Override
    public ShelvesResponse getShelve(Long id) {
        Shelve shelve = shelvesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shelve not found!"));

        return shelveMapper.toShelveResponse(shelve);
    }

    @Override
    public ShelvesResponse createShelve(ShelvesRequest shelvesRequest) {
        Store store = storeRepository.findById(shelvesRequest.getStoreId()).orElseThrow(() -> new ResourceNotFoundException("Store not found!"));
        ShelveType shelveType = shelvesTypeRepository.findById(shelvesRequest.getShelvesTypeId()).orElseThrow(() -> new ResourceNotFoundException("Shelve type not found!"));

        Shelve shelve = shelveMapper.toShelve(shelvesRequest);
        shelve.setStore(store);
        shelve.setShelvesType(shelveType);
        Shelve shelveSaved = shelvesRepository.save(shelve);

        return shelveMapper.toShelveResponse(shelveSaved);
    }
}
