package com.example.shelve.mapper;

import com.example.shelve.dto.request.ShelvesRequest;
import com.example.shelve.dto.response.ImageResponse;
import com.example.shelve.dto.response.ProductResponse;
import com.example.shelve.dto.response.ShelvesResponse;
import com.example.shelve.entities.Product;
import com.example.shelve.entities.Shelve;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ShelveMapper {

    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private StoreMapper storeMapper;

    public ShelvesResponse toShelveResponse(Shelve shelve) {
        Set<ImageResponse> imageResponseSet = new HashSet<>();
        shelve.getImages().forEach(image ->
                imageResponseSet.add(
                        imageMapper.toImageResponse(image)));

        Set<ProductResponse> productResponseSet = new HashSet<>();
        shelve.getShelvesProducts().forEach(shelveProduct -> {
            if (shelveProduct.isStatus()) {
                Product product = productRepository.findById(shelveProduct.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));

                productResponseSet.add(productMapper.toProductResponse(product));
            }
        });

        return ShelvesResponse.builder()
                .id(shelve.getId())
                .name(shelve.getName())
                .description(shelve.getDescription())
                .status(shelve.isStatus())
                .store(storeMapper.toStoreResponse(shelve.getStore()))
                .shelveType(shelve.getShelveType())
                .images(imageResponseSet)
                .products(productResponseSet)
                .build();
    }

    public Shelve toShelve(ShelvesRequest shelvesRequest) {
        return Shelve.builder()
                .name(shelvesRequest.getName())
                .description(shelvesRequest.getDescription())
                .status(shelvesRequest.isStatus())
                .build();
    }

}
