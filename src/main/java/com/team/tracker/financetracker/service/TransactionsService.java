package com.team.tracker.financetracker.service;

import com.team.tracker.financetracker.model.Transactions;
import com.team.tracker.financetracker.repository.TransactionsReposirory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionsService {

    private final TransactionsReposirory transactionsReposirory;

    @Autowired
    public TransactionsService(TransactionsReposirory transactionsReposirory){
        this.transactionsReposirory = transactionsReposirory;
    }

    public List<Transactions> getUserTransactions(UUID userId){
        return transactionsReposirory.findByUserId(userId);
    }

    @Transactional
    public Transactions save(Transactions transaction) {

        //todo проверка на корректность данных

        return transactionsReposirory.save(transaction);
    }
}
