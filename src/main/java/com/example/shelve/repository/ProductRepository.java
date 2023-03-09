package com.example.shelve.repository;

import com.example.shelve.entities.Category;
import com.example.shelve.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p FROM Product p join p.category" +
            " WHERE p.category IN :categories" +
            " AND (LOWER(p.name) LIKE %:keyword%)" +
            " AND (:brandId = 0L OR p.brand.id = :brandId)")
    Page<Product> findByKeywordWithFilter(
            @Param("categories") List<Category> categories,
            @Param("keyword") String keyword,
            @Param("brandId") long brandId,
            Pageable pageable
    );
}
