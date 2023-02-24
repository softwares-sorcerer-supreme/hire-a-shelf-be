package com.example.shelve.entities;

import com.example.shelve.entities.enums.EStatus;
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

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

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
    private Set<Shelve> shelves;

    @OneToMany(mappedBy = "store")
    private Set<Order> orders;

    @OneToOne(mappedBy = "store")
    @JsonIgnore
    private Account account;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "retailer_id")
    private Retailer retailer;

    @OneToMany(mappedBy = "store")
    private Set<StoreCategory> storeCategories;

}
