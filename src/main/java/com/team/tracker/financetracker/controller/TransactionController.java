package com.team.tracker.financetracker.controller;

import com.team.tracker.financetracker.model.Transactions;

import com.team.tracker.financetracker.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions") //todo уточнить путь у фронта
@CrossOrigin(origins = "http://localhost:63342")
public class TransactionController {

    private final TransactionsService transactionsService;

    @Autowired
    public TransactionController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @PostMapping("/new") //todo уточнить путь у фронта
    public ResponseEntity<Transactions> addTransaction(@RequestBody Transactions transaction){
        return ResponseEntity.ok(transactionsService.save(transaction));
    }
}
