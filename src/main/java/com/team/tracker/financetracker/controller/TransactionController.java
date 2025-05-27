package com.team.tracker.financetracker.controller;

import com.team.tracker.financetracker.dto.CreateTransactionRequestDto;
import com.team.tracker.financetracker.model.Transaction;

import com.team.tracker.financetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
@CrossOrigin(origins = "http://localhost:63342")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionsService) {
        this.transactionService = transactionsService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody CreateTransactionRequestDto request){
        return ResponseEntity.ok(transactionService.save(request));
    }
}
