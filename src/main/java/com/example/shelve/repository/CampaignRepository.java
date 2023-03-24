package com.example.shelve.repository;

import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.CampaignProduct;
import com.example.shelve.entities.Category;
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
            " AND (c.EStatus IN (:states) OR :states IS NULL)" +
            " AND (:brandId = 0L OR c.brand.id = :brandId)")
    Page<Campaign> findByKeywordWithFilter(
            @Param("states") List<EStatus> states,
            @Param("keyword") String keyword,
            @Param("brandId") long brandId,
            Pageable pageable
    );

    @Query(value = "SELECT DISTINCT c FROM Campaign c JOIN c.campaignProducts cp JOIN cp.product p JOIN p.category cate " +
            "WHERE LOWER(c.title) LIKE %:keyword% " +
            " AND (c.EStatus IN (:states) OR :states IS NULL)" +
            " AND (:city = '' OR c.city = :city) " +
            " AND (COALESCE(:categoriesName, NULL) IS NULL OR cate.name IN :categoriesName)" +
            " AND c.id NOT IN :campaignIds")
    Page<Campaign> findByKeywordWithFilterForHomePage(
            @Param("states") List<EStatus> states,
            @Param("keyword") String keyword,
            @Param("categoriesName") List<String> categoriesName,
            @Param("city") String city,
            @Param("campaignIds") List<Long> campaignIds,
            Pageable pageable
    );}
