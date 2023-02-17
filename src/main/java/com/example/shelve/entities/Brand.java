package com.example.shelve.entities;

import com.example.shelve.entities.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "brand")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand")
    private Set<Location> location;

    @Column(name = "phone")
    private String phone;

    @Column(name = "logo")
    private String logo;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EStatus EStatus;

    @OneToMany(mappedBy = "brand")
    @JsonIgnore
    private Set<Campaign> campaigns;

    @OneToMany(mappedBy = "brand")
    private Set<Product> products;

    @OneToOne(mappedBy = "brand")
    @JsonIgnore
    private Account account;
}
