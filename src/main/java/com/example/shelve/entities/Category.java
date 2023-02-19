package com.example.shelve.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "category")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    @OneToMany(mappedBy = "category")
    private Set<StoreCategory> storeCategories;
}
