package com.example.shelve.repository;

import com.example.shelve.entities.Shelve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelveRepository extends JpaRepository<Shelve, Long> {
}
