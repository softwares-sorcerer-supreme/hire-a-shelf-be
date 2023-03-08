package com.example.shelve.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "shelves_products")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShelvesProducts {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shelve_id")
    private Shelves shelves;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status")
    private boolean status;
}
