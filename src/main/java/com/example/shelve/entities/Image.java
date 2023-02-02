package com.example.shelve.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "image")
public class Image implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "imgUrl")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Products products;

    @ManyToOne
    @JoinColumn(name = "shelves_id")
    private Shelves shelves;
}
