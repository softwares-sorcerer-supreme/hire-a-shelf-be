package com.example.shelve.repository;

import com.example.shelve.entities.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    @Query(value = "SELECT c FROM Campaign c" +
            " WHERE LOWER(c.title) LIKE %:keyword%" +
            " AND c.brand.id = :brandId")
    Page<Campaign> findByKeywordWithFilter(
            @Param("keyword") String keyword,
            @Param("brandId") long brandId,
            Pageable pageable
    );
}
