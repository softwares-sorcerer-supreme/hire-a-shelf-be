package com.example.shelve.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "campaign_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private Product product;

    @Column(name = "status")
    private boolean status;

}
