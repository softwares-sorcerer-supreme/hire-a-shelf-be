package com.example.shelve.repository;

import com.example.shelve.entities.Account;
import com.example.shelve.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
