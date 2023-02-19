package com.example.shelve.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "products")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Products implements Serializable {

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

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "price")
    private String price;

    @Column(name = "imgURL")
    private String imgURL;

    @OneToMany(mappedBy = "products")
    private Set<Category> categories;

    @OneToMany(mappedBy = "products")
    private Set<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "products")
    private Set<ShelvesProducts> shelvesProducts;

    @OneToMany(mappedBy = "products")
    @JsonIgnore
    private Set<CampaignProduct> campaignProducts;
}
