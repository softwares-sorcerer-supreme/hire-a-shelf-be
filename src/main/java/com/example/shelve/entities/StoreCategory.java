package com.example.shelve.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "store_category")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreCategory {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
