package com.example.shelve.services.impl;

import com.example.shelve.dto.request.ProductRequest;
import com.example.shelve.dto.response.APIResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.ProductResponse;
import com.example.shelve.entities.Brand;
import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.Category;
import com.example.shelve.entities.Product;
import com.example.shelve.exception.BadRequestException;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.ProductMapper;
import com.example.shelve.repository.BrandRepository;
import com.example.shelve.repository.CategoryRepository;
import com.example.shelve.repository.ProductRepository;
import com.example.shelve.services.ProductService;
import com.example.shelve.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private static final int pageSize = 8;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductResponse> getAllProduct() {
        List<ProductResponse> productResponseList = new ArrayList<>();
        productRepository.findAll().forEach(product -> productResponseList.add(productMapper.toProductResponse(product)));

        return productResponseList;
    }

    @Override
    @CacheEvict(value = "product", allEntries = true)
    public ProductResponse creteProduct(ProductRequest productRequest) {
        log.error("add product ddi qua day");

        productRequest.setStatus(true);
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));

        Brand brand = brandRepository.findById(productRequest.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found!"));

        Product product = productMapper.toProduct(productRequest);

        product.setImgURL(storageService.uploadFile(productRequest.getImgMultipart()));
        product.setCategory(category);
        product.setBrand(brand);

        Product productSaved = productRepository.save(product);

        return productMapper.toProductResponse(productSaved);
    }

    @Override
    @Cacheable(value = "product", key = "{#brandId, #page, #keyword}")
    public APIResponse<List<ProductResponse>> getAllProductWithFilter(long brandId, int page, String keyword, List<Long> categoriesId) {
        log.error("Product ddi qua day");

        Pageable pageable;
        pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "name");

        //By default, filter by all categories, else filter by categories that user choose.
        List<Category> categories;
        if (categoriesId == null) {
            categories = categoryRepository.findAll();
        } else {
            categories = categoryRepository.findCategoriesByIdIsIn(categoriesId);
        }

        Page<Product> result;
        result = productRepository.findByKeywordWithFilter
                (categories, keyword.toLowerCase(), brandId, pageable);

        List<ProductResponse> productResponseList = new ArrayList<>();
        result.toList().forEach((x -> productResponseList.add(productMapper.toProductResponse(x))));
        return new APIResponse<>(result.getTotalPages(), productResponseList);
    }

    @Override
    public List<ProductResponse> getAllBrandsAvailableProductWithoutFilter(long brandId) {
        List<ProductResponse> productResponseList = new ArrayList<>();
        List<Product> products = productRepository.findProductsByBrandIdAndStatus(brandId, true);
        products.forEach((x -> productResponseList.add(productMapper.toProductResponse(x))));
        return productResponseList;
    }

    @Override
    @CachePut(value = "product", key = "#productId")
    public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));

        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));

        if(product.getBrand().getId() != productRequest.getBrandId())
            throw new BadRequestException("You do not have permission to edit this product");

        product.setName(productRequest.getName());
        product.setDescription(product.getDescription());
        product.setStatus(product.isStatus());
        product.setQuantity(product.getQuantity());
        product.setPrice(product.getPrice());
        product.setImgURL(storageService.uploadFile(productRequest.getImgMultipart()));
        product.setCategory(category);

        Product productSaved = productRepository.save(product);

        return productMapper.toProductResponse(productSaved);
    }


}
