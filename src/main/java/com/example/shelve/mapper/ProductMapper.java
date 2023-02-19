package com.example.shelve.mapper;

import com.example.shelve.dto.request.ProductRequest;
import com.example.shelve.dto.response.ProductResponse;
import com.example.shelve.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .status(productRequest.isStatus())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .imgURL(productRequest.getImgURL())
                .category(productRequest.getCategory())
                .brand(productRequest.getBrand())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .status(product.isStatus())
                .name(product.getName())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .imgURL(product.getImgURL())
//                .category(product.getCategory())
//                .brand(product.getBrand())
                .build();
    }

}
