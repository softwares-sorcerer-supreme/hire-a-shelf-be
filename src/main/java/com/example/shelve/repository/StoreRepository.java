package com.example.shelve.repository;

import com.example.shelve.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Set<Store> findAllByLocationCity(String city);
}
