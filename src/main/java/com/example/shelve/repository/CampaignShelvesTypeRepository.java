package com.example.shelve.repository;

import com.example.shelve.entities.CampaignShelvesType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignShelvesTypeRepository extends JpaRepository<CampaignShelvesType, Long> {
    List<CampaignShelvesType> findByCampaign_Id(Long campaignId);

}
