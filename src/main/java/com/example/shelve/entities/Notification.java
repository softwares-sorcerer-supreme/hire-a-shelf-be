package com.example.shelve.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "notification")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", columnDefinition = "varchar")
    private String title;

    @Column(name = "body", columnDefinition = "varchar")
    private String body;

    @ManyToOne
    @JoinColumn(name = "account_id")

    private Account account;


}
