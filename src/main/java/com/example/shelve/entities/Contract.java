package com.example.shelve.entities;

import com.example.shelve.entities.enums.EStatus;
import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "contract")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contract implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "cancel_date")
    private Date cancelDate;

    @Column(name = "approval_date")
    private Date approvalDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EStatus EStatus;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
