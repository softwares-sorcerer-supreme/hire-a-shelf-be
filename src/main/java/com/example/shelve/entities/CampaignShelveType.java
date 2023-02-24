package com.example.shelve.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "campaign_shelve_type")
public class CampaignShelveType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @ManyToOne
    @JoinColumn(name = "shelve_type_id")
    private ShelveType shelvesType;

    @Column(name = "status")
    private boolean status;

}
