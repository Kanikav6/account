package com.assignment.newaccount.repository;

import com.assignment.newaccount.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AccountRepo extends JpaRepository<Account, Long> {
}
