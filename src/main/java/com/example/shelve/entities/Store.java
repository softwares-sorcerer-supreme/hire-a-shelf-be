package com.example.shelve.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "store")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "name", columnDefinition = "varchar")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "logo", columnDefinition = "text")
    private String logo;

    @Column(name = "description", columnDefinition = "varchar")
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
    @JsonIgnore
    private Account account;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @OneToMany(mappedBy = "store")
    private Set<StoreCategory> storeCategories;

}
