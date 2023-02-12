package com.example.shelve.repository;

import com.example.shelve.entities.Shelves;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelvesRepository extends JpaRepository<Shelves, Long> {
}
