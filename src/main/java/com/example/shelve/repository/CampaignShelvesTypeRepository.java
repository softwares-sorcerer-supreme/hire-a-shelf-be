package com.example.shelve.repository;

import com.example.shelve.entities.CampaignShelveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignShelvesTypeRepository extends JpaRepository<CampaignShelveType, Long> {
}
