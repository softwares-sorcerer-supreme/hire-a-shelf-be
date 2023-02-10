package com.example.shelve.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "shelves")
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
    private Set<Drawers> drawers;

    @OneToMany(mappedBy = "shelves")
    private Set<Image> images;

}
