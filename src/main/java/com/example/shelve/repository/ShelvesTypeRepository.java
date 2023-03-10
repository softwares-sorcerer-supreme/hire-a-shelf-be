package com.example.shelve.repository;

import com.example.shelve.entities.ShelvesType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShelvesTypeRepository extends JpaRepository<ShelvesType, Long> {
    List<ShelvesType> findShelvesTypesByStatus(boolean status);
}
