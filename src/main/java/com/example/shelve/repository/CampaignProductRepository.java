package com.example.shelve.repository;

import com.example.shelve.entities.CampaignProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignProductRepository extends JpaRepository<CampaignProduct, Long> {

    public List<CampaignProduct> findCampaignProductByCampaign_Id(Long id);
}
