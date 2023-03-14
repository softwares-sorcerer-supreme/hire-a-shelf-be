package com.example.shelve.repository;

import com.example.shelve.entities.FirebaseNotiToken;
import com.example.shelve.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface FirebaseTokenRepository extends JpaRepository<FirebaseNotiToken, Long> {
    FirebaseNotiToken findByTokenAndAccountId(String token, Long accountId);
    Set<FirebaseNotiToken> findAllByStatusAndAccountId(boolean status, Long id);

    Set<FirebaseNotiToken> findAllByStatusAndAccountStoreLocationCity(boolean status, String city);

    List<FirebaseNotiToken> findAllByStatusAndAccountStore(boolean status, Store store);
}
