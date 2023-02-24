package com.example.shelve.repository;

import com.example.shelve.entities.ShelveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelvesTypeRepository extends JpaRepository<ShelveType, Long> {
}
