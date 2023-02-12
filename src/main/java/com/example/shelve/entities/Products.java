package com.example.shelve.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "products")
public class Products implements Serializable {

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

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "price")
    private String price;

    @Column(name = "imgURL")
    private String imgURL;

    @OneToMany(mappedBy = "products")
    private Set<Category> categories;

    @OneToMany(mappedBy = "products")
    private Set<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToMany(mappedBy = "products")
    private Set<Shelves> shelves;

    @ManyToMany(mappedBy = "products")
    private Set<Campaign> campaigns;
}
