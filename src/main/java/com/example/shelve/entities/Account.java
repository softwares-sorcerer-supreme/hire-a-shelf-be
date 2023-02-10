package com.example.shelve.entities;


import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "status")
    private boolean status;

    @OneToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
