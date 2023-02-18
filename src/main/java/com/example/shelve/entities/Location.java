package com.example.shelve.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name= "location")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "district")
    private String district;

    @Column(name = "address")
    private String address;

    @Column(name = "ward")
    private String ward;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "location")
    private Set<Store> store;

    @OneToOne(mappedBy = "location")
    private Registration registration;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "status")
    private boolean status;
}
