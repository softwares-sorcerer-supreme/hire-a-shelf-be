package com.example.shelve.entities;


import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @OneToOne(mappedBy = "admin")
    private Account account;

}
