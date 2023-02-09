package com.example.shelve.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_detail")
public class OrderDetail implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "sales")
    private String sales;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Products products;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;

}
