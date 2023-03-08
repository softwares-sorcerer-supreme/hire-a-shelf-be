package com.example.shelve.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shelve_type")
public class ShelveType implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "shelveType")
    @JsonIgnore
    private Set<Shelve> shelves;

    @OneToMany(mappedBy = "shelveType")
    private Set<CampaignShelveType> campaignShelveTypes;
}
