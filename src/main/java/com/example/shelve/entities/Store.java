package com.example.shelve.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "retailer")
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

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
}
