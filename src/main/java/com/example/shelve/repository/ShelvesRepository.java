package com.example.shelve.repository;

import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.Shelves;
import com.example.shelve.entities.enums.EStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShelvesRepository extends JpaRepository<Shelves, Long> {


    @Query(value = "SELECT s FROM Shelves s" +
            " WHERE LOWER(s.name) LIKE %:keyword%" +
            " AND (s.status = :states)" +
            " AND (:storeId = 0L OR s.store.id = :storeId)")
    Page<Shelves> findByKeywordWithFilter(
            @Param("state") boolean state,
            @Param("keyword") String keyword,
            @Param("storeId") long storeId,
            Pageable pageable
    );

    @Query(value = "SELECT s FROM Shelves s" +
            " WHERE LOWER(s.name) LIKE %:keyword%" +
            " AND (:storeId = 0L OR s.store.id = :storeId)")
    Page<Shelves> findAllByKeywordWithFilter(
            @Param("keyword") String keyword,
            @Param("storeId") long storeId,
            Pageable pageable
    );
}
