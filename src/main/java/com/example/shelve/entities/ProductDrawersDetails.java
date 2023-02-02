package com.example.shelve.entities;

import javax.persistence.*;

@Entity
@Table(name = "droductdrawersdetails")
public class ProductDrawersDetails {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "productDrawersDetails_id")
    private Drawers drawers;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Products products;

}
