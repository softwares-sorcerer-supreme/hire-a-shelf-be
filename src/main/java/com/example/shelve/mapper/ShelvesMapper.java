package com.example.shelve.mapper;

import com.example.shelve.dto.request.ShelvesRequest;
import com.example.shelve.dto.response.ImageResponse;
import com.example.shelve.dto.response.ProductResponse;
import com.example.shelve.dto.response.ShelvesResponse;
import com.example.shelve.entities.Product;
import com.example.shelve.entities.Shelves;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ShelvesMapper {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private ShelvesTypeMapper shelvesTypeMapper;


    public ShelvesResponse toShelveResponse(Shelves shelves) {
        Set<ImageResponse> imageResponseSet = new HashSet<>();


        Set<ProductResponse> productResponseSet = new HashSet<>();
        shelves.getShelvesProducts().forEach(shelveProduct -> {
            if (shelveProduct.isStatus()) {
                Product product = productRepository.findById(shelveProduct.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));

                productResponseSet.add(productMapper.toProductResponse(product));
            }
        });

        return ShelvesResponse.builder()
                .id(shelves.getId())
                .name(shelves.getName())
                .description(shelves.getDescription())
                .status(shelves.isStatus())
                .store(storeMapper.toStoreResponse(shelves.getStore()))
                .shelvesTypeResponse(shelvesTypeMapper.toShelvesTypeResponse(shelves.getShelvesType()))
                .images(imageResponseSet)
                .products(productResponseSet)
                .build();
    }

    public Shelves toShelve(ShelvesRequest shelvesRequest) {
        return com.example.shelve.entities.Shelves.builder()
                .name(shelvesRequest.getName())
                .description(shelvesRequest.getDescription())
                .status(shelvesRequest.isStatus())
                .build();
    }

}
