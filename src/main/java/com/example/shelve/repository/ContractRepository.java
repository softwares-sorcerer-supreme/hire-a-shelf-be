package com.example.shelve.repository;

import com.example.shelve.entities.Contract;
import com.example.shelve.entities.enums.EStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Page<Contract> findAllByStoreIdAndEStatusIn(Long storeId, List<EStatus> eStatuses, Pageable pageable);
    Page<Contract> findAllByStoreId(Long storeId, Pageable pageable);
    List<Contract> findAllByStoreId(Long storeId);
    Optional<Contract> findByStoreIdAndCampaignId(Long storeId, Long campaignId);

}
