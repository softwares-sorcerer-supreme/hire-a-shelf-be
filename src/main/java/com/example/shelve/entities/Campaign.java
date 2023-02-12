package com.example.shelve.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "campaign")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Campaign implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "duration")
    private int duration;

    @Column(name = "imgURL")
    private String imgURL;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "campaign")
    private Set<Contract> contracts;

    @ManyToOne
    @JoinColumn(name = "shelves_id")
    private Shelves shelves;

    @OneToMany(mappedBy = "campaign")
    private Set<Order> orders;

    @ManyToMany
    @JoinTable(name = "campaign_product", joinColumns = @JoinColumn(name = "campaign_id"), inverseJoinColumns = @JoinColumn(name = "products_id"))
    private Set<Products> products;

}
