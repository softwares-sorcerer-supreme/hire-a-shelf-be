package com.example.shelve.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "status")
    private boolean status;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Products products;
}
