package com.example.shelve.repository;

import com.example.shelve.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDrawersDetailsRepository extends JpaRepository<Campaign, Long> {
}
