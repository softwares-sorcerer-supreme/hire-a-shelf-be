package com.example.shelve.entities;

import com.example.shelve.entities.enums.EStatus;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "registration")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Registration {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name", columnDefinition = "varchar")
    private String name;

    @Column(name = "note", columnDefinition = "varchar")
    private String note;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "type_account")
    private String typeAccount;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "is_register_by_google")
    private boolean isRegisterByGoogle;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EStatus EStatus;
}
