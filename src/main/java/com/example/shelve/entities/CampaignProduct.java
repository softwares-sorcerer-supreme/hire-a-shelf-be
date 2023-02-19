package com.example.shelve.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "campaign_product")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignProduct {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;

}
