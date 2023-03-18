package com.example.shelve.repository;

import com.example.shelve.entities.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreCategoryRepository extends JpaRepository<StoreCategory, Long> {
    List<StoreCategory> findAllByStoreId(Long storeId);
    Optional<StoreCategory> findByStoreIdAndCategoryId(Long storeId, Long categoryId);
}
