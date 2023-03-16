package com.example.shelve.entities;

import com.example.shelve.entities.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
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

    @Column(name = "name", columnDefinition = "varchar")
    private String name;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "phone")
    private String phone;

    @Column(name = "logo", columnDefinition = "varchar")
    private String logo;

    @Column(name = "participate_date")
    private Date participateDate;

    @Column(name = "description", columnDefinition = "varchar")
    private String description;

    @OneToMany(mappedBy = "brand")
    @JsonIgnore
    private Set<Campaign> campaigns;

    @OneToMany(mappedBy = "brand")
    private Set<Product> products;

    @OneToOne(mappedBy = "brand")
    @JsonIgnore
    private Account account;

    @Column(name = "status")
    private boolean status;
}
