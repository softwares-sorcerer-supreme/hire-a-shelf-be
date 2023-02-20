package com.example.shelve.repository;

import com.example.shelve.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserName(String userName);

    Optional<Account> findByEmail(String email);

}
