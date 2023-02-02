package com.example.shelve.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "drawers")
public class Drawers implements Serializable {

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
    @JoinColumn(name = "shelves_id")
    private Shelves shelves;

    @OneToMany(mappedBy = "drawers")
    private Set<Campaign> campaigns;

    @OneToMany(mappedBy = "drawers")
    private Set<ProductDrawersDetails> productDrawersDetails;
}
