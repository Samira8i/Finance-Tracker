package com.team.tracker.financetracker.model;


import com.team.tracker.financetracker.enums.TransactionType;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "amount", nullable = false)
    private double amount;


    @Column(name = "transaction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "transaction_date") //todo localDateTime
    private LocalDate transactionDate;

    public Transaction(){}

    public Transaction(float amount, TransactionType transactionType){
        setAmount(amount);
        setTransactionType(transactionType);
        setTransactionDate(LocalDate.now());

    }

    public TransactionType getTransactionType() {return transactionType;}

    public double getAmount() { return amount; }

    public User getUser() { return user; }

    public Category getCategory() {return category; }

    public UUID getId() {return id; }

    public LocalDate getTransactionDate() {return transactionDate; }

    public void setId(UUID id) {this.id = id; }


    public void setAmount(float amount) { this.amount = amount; }

    public void setTransactionType(TransactionType transactionType) {this.transactionType = transactionType;
    }

    public void setTransactionDate(LocalDate transactionDate) {this.transactionDate = transactionDate;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

