package com.example.shelve.entities;


import javax.persistence.*;

@Entity
@Table(name = "shelves_products")
public class ShelvesProducts {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shelve_id")
    private Shelves shelves;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;
}
