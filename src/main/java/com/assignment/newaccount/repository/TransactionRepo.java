package com.assignment.newaccount.repository;

import com.assignment.newaccount.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
}
