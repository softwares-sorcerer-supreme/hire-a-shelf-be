package com.example.shelve.repository;

import com.example.shelve.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findCategoriesByIdIsIn(List<Long> categoryNames);

}
