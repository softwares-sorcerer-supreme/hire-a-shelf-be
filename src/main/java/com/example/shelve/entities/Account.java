package com.example.shelve.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "account")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "account")
    private Set<FirebaseNotiToken> fireBaseNotiTokens;

    @OneToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private Set<Notification> notifications;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", admin=" + admin +
                ", store=" + store +
                ", brand=" + brand +
                '}';
    }
}
