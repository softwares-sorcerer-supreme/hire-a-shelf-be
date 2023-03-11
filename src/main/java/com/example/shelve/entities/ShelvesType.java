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
@Table(name = "shelves_type")
public class ShelvesType implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", columnDefinition = "varchar")
    private String name;

    @Column(name = "description", columnDefinition = "varchar")
    private String description;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "shelvesType")
    @JsonIgnore
    private Set<Shelves> shelves;

    @OneToMany(mappedBy = "shelvesType")
    private Set<CampaignShelvesType> campaignShelvesTypes;
}
