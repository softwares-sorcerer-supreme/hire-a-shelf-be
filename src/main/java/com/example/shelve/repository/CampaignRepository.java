package com.example.shelve.repository;

import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.enums.EStatus;
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
            " AND (:states IS NULL OR c.EStatus IN (:states))" +
            " AND (:brandId = 0L OR c.brand.id = :brandId)")
    Page<Campaign> findByKeywordWithFilter(
            @Param("states") List<EStatus> states,
            @Param("keyword") String keyword,
            @Param("brandId") long brandId,
            Pageable pageable
    );
}
