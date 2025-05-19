package com.team.tracker.financetracker.service;

import com.team.tracker.financetracker.model.Transaction;
import com.team.tracker.financetracker.repository.TransactionReposirory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionReposirory transactionReposirory;

    @Autowired
    public TransactionService(TransactionReposirory transactionReposirory){
        this.transactionReposirory = transactionReposirory;
    }

    public List<Transaction> getUserTransaction(UUID userId){
        return transactionReposirory.findByUserId(userId);
    }

    @Transactional
    public Transaction save(Transaction transaction) {

        //todo проверка на корректность данных

        return transactionReposirory.save(transaction);
    }
}
