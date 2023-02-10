package com.example.shelve.repository;

import com.example.shelve.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Campaign, Long> {
}
