package com.team.tracker.financetracker.dto;

import com.team.tracker.financetracker.enums.TransactionType;
import java.time.LocalDateTime;

import java.util.UUID;

public class CreateTransactionRequestDto {

    private UUID userId;
    private UUID categoryId;

    private Double amount;
    private TransactionType transactionType;
    private LocalDateTime transactionDate;

    public CreateTransactionRequestDto(UUID categoryId, UUID userId, Double amount, TransactionType transactionType, LocalDateTime transactionDate) {
        this.categoryId = categoryId;
        this.userId = userId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    public CreateTransactionRequestDto() {
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

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
