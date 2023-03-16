package com.example.shelve.entities;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "firebase_token")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FirebaseNotiToken {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "token", columnDefinition = "Text")
    private String token;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
