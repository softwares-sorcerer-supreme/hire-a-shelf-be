package com.example.shelve.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "shelves")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shelves implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", columnDefinition = "varchar")
    private String name;

    @Column(name = "description", columnDefinition = "varchar")
    private String description;

    @Column(name = "status")
    private boolean status;

    @Column(name = "image", columnDefinition = "TEXT")
    private String imgURL;

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonIgnore
    private Store store;

    @ManyToOne
    @JoinColumn(name = "shelves_type_id")
    private ShelvesType shelvesType;

    @OneToMany(mappedBy = "shelves")
    private Set<ShelvesProducts> shelvesProducts;

}
