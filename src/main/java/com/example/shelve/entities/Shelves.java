package com.example.shelve.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "shelves")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shelves implements Serializable {
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

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "shelves_type_id")
    private ShelvesType shelvesType;

    @OneToMany(mappedBy = "shelves")
    private Set<ShelvesProducts> shelvesProducts;

    @OneToMany(mappedBy = "shelves")
    private Set<Image> images;

    @OneToMany(mappedBy = "shelves")
    @JsonIgnore
    private Set<Campaign>  campaigns;

}
