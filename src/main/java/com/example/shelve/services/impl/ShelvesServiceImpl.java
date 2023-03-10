package com.example.shelve.services.impl;

import com.example.shelve.dto.request.ShelvesRequest;
import com.example.shelve.dto.response.APIResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.ShelvesResponse;
import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.Shelves;
import com.example.shelve.entities.ShelvesType;
import com.example.shelve.entities.Store;
import com.example.shelve.entities.enums.EStatus;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.ShelvesMapper;
import com.example.shelve.repository.ShelvesRepository;
import com.example.shelve.repository.ShelvesTypeRepository;
import com.example.shelve.repository.StoreRepository;
import com.example.shelve.services.ShelvesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShelvesServiceImpl implements ShelvesService {

    @Autowired
    private ShelvesRepository shelvesRepository;
    @Autowired
    private ShelvesMapper shelvesMapper;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ShelvesTypeRepository shelvesTypeRepository;

    @Override
    public ShelvesResponse getShelve(Long id) {
        Shelves shelves = shelvesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shelve not found!"));

        return shelvesMapper.toShelveResponse(shelves);
    }

    @Override
    public ShelvesResponse createShelve(ShelvesRequest shelvesRequest) {
        Store store = storeRepository.findById(shelvesRequest.getStoreId()).get();
        ShelvesType shelvesType = shelvesTypeRepository.findById(shelvesRequest.getShelvesTypeId()).orElseThrow(() -> new ResourceNotFoundException("Shelve type not found!"));

        Shelves shelves = shelvesMapper.toShelve(shelvesRequest);
        shelves.setStore(store);
        shelves.setShelvesType(shelvesType);
        Shelves shelvesSaved = shelvesRepository.save(shelves);

        return shelvesMapper.toShelveResponse(shelvesSaved);
    }

    @Override
    public ShelvesResponse updateShelve(Long id, ShelvesRequest shelvesRequest) {
        Store store = storeRepository.findById(shelvesRequest.getStoreId()).get();
        ShelvesType shelvesType = shelvesTypeRepository.findById(shelvesRequest.getShelvesTypeId()).orElseThrow(() -> new ResourceNotFoundException("Shelve type not found!"));

        Shelves shelves = shelvesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shelve not found!"));

        shelves.setName(shelvesRequest.getName());
        shelves.setDescription(shelvesRequest.getDescription());
        shelves.setShelvesType(shelvesType);
        shelves.setStore(store);

        Shelves shelvesSaved = shelvesRepository.save(shelves);

        return shelvesMapper.toShelveResponse(shelvesSaved);
    }

    @Override
    public APIResponse<List<ShelvesResponse>> getListShelvesWithFilter(long storeId, String keyword, int page, String status) {
        Pageable pageable;
        pageable = PageRequest.of(page, 6, Sort.Direction.DESC , "name");
        Page<Shelves> result;
        if (!status.equals("none")){
            boolean state = Boolean.parseBoolean(status);
            result = shelvesRepository.findByKeywordWithFilter
                    (state, keyword.toLowerCase(), storeId ,pageable);
        }else {
            result = shelvesRepository.findAllByKeywordWithFilter
                    (keyword.toLowerCase(), storeId ,pageable);
        }

        List<ShelvesResponse> shelvesResponses = new ArrayList<>();
        result.toList().forEach((x -> shelvesResponses.add(shelvesMapper.toShelveResponse(x))));
        return new APIResponse<>(result.getTotalPages(), shelvesResponses);
    }
}
