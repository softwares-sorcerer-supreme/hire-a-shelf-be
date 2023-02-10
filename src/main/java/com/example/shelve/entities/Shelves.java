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
    @JoinColumn(name = "shelvesType_id")
    private ShelvesType shelvesType;

    @ManyToMany
    @JoinTable(name = "shelves_products", joinColumns = @JoinColumn(name = "shelves_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Products> products;

    @OneToMany(mappedBy = "shelves")
    private Set<Image> images;

    @OneToMany(mappedBy = "shelves")
    private Set<Campaign>  campaigns;

}
