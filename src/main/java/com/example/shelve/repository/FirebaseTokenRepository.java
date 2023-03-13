package com.example.shelve.repository;

import com.example.shelve.entities.FirebaseNotiToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirebaseTokenRepository extends JpaRepository<FirebaseNotiToken, Long> {
    FirebaseNotiToken findByTokenAndAccountId(String token, Long accountId);
}
