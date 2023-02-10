package com.example.shelve.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "[order]")
public class Order implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "date")
    private Date date;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;
}
