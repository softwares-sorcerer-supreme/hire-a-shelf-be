package com.example.shelve.mapper;

import com.example.shelve.dto.response.CampaignProductResponse;
import com.example.shelve.dto.response.ProductResponse;
import com.example.shelve.entities.CampaignProduct;
import com.example.shelve.entities.Products;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toProductResponse (Products products){
        ProductResponse productResponse = ProductResponse.builder()
                .id(products.getId())
                .status(products.isStatus())
                .name(products.getName())
                .description(products.getDescription())
                .quantity(products.getQuantity())
                .price(products.getPrice())
                .build();

        return productResponse;
    }
}
