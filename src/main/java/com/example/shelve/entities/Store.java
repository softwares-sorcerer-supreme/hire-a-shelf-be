package com.example.shelve.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "store")
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "logo")
    private String logo;

    @Column(name = "description")
    private String description;

    @Column(name = "participate_date")
    private Date participateDate;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "store")
    private Set<Contract> contracts;

    @OneToMany(mappedBy = "store")
    private Set<Shelves> shelves;

    @OneToMany(mappedBy = "store")
    private Set<Order> orders;

    @OneToOne(mappedBy = "store")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "retailer_id")
    private Retailer retailer;

    @ManyToMany
    @JoinTable(name = "store_category", joinColumns = @JoinColumn(name = "store_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category>  categories;

}
