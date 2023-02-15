package com.example.shelve.entities;

import com.example.shelve.entities.enums.Status;
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

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name")
    private String name;

    @Column(name = "note")
    private String note;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "type_account")
    private String typeAccount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
}
