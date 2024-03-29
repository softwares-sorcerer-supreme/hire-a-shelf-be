package com.example.shelve.entities;

import com.example.shelve.entities.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "campaign")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Campaign implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "title", columnDefinition = "varchar")
    private String title;

    @Column(name = "content", columnDefinition = "varchar")
    private String content;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "duration")
    private int duration;

    @Column(name = "imgURL", columnDefinition = "varchar")
    private String imgURL;

    @Column(name = "city", columnDefinition = "varchar")
    private String city;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "campaign")
    private Set<Contract> contracts;

    @OneToMany(mappedBy = "campaign")
    private Set<Order> orders;

    @OneToMany(mappedBy = "campaign")
    @JsonIgnore
    private Set<CampaignProduct> campaignProducts;

    @OneToMany(mappedBy = "campaign")
    private Set<CampaignShelvesType> campaignShelvesTypes;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EStatus EStatus;

}
