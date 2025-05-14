package com.team.tracker.financetracker.repository;

import com.team.tracker.financetracker.enums.TransactionType;
import com.team.tracker.financetracker.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionsReposirory extends JpaRepository<Transactions, UUID> {
    List<Transactions> findByUserId(UUID userId);
    List<Transactions> findByCategoryId(UUID categoryId);
    List<Transactions> findByTransactionType(TransactionType transactionType);

}
