package com.example.shelve.repository;

import com.example.shelve.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrawersRepository extends JpaRepository<Campaign, Long> {
}
