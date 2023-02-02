package com.example.shelve.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "registration")
public class Registration {

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

    @Column(name = "name")
    private String name;

    @Column(name = "note")
    private String note;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "typeAccount")
    private String typeAccount;



}
