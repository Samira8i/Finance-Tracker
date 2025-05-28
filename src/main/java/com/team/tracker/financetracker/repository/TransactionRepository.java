package com.team.tracker.financetracker.repository;

import com.team.tracker.financetracker.enums.TransactionType;
import com.team.tracker.financetracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByUserId(UUID userId);
    List<Transaction> findByCategoryId(UUID categoryId);
    List<Transaction> findByTransactionType(TransactionType transactionType);

}
