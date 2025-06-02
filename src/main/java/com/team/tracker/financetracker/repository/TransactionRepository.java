package com.team.tracker.financetracker.repository;

import com.team.tracker.financetracker.enums.TransactionType;
import com.team.tracker.financetracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByUserId(UUID userId);
    List<Transaction> findByCategoryId(UUID categoryId);
    List<Transaction> findByTransactionType(TransactionType transactionType);
    List<Transaction> findByUserIdOrderByTransactionTimeDesc(UUID userId);


    @Query("SELECT " +
            "COALESCE(SUM(CASE WHEN t.transactionType = 'INCOME' THEN t.amount ELSE 0 END), 0) - " +
            "COALESCE(SUM(CASE WHEN t.transactionType = 'EXPENSE' THEN t.amount ELSE 0 END), 0) " +
            "FROM Transaction t WHERE t.user.id = :userId")
    Double sumIncomeExpense(@Param("userId") UUID userId);

}
