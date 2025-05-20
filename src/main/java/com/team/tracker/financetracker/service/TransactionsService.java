package com.team.tracker.financetracker.service;

import com.team.tracker.financetracker.model.Transactions;
import com.team.tracker.financetracker.repository.TransactionsReposirory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class TransactionsService {

    private final TransactionsReposirory transactionsReposirory;


    public TransactionsService(TransactionsReposirory transactionsReposirory){
        this.transactionsReposirory = transactionsReposirory;
    }

    public List<Transactions> getUserTransactions(UUID userId){
        return transactionsReposirory.findByUserId(userId);

    }
}
