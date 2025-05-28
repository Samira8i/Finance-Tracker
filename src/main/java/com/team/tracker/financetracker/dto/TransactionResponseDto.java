package com.team.tracker.financetracker.dto;

import com.team.tracker.financetracker.enums.TransactionType;
import com.team.tracker.financetracker.model.Transaction;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionResponseDto {

    private UUID userId;
    private UUID categoryId;

    private Double amount;
    private TransactionType transactionType;
    private LocalDateTime transactionDate;

    public static TransactionResponseDto responseFromTransaction(Transaction transaction) {
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setUserId(transaction.getUser().getId());
        dto.setCategoryId(transaction.getCategory().getId());
        dto.setAmount(transaction.getAmount());
        dto.setTransactionType(transaction.getTransactionType());
        dto.setTransactionDate(transaction.getTransactionDate());
        return dto;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
