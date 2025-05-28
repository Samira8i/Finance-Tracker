package com.team.tracker.financetracker.controller;

import com.team.tracker.financetracker.dto.CreateTransactionRequestDto;
import com.team.tracker.financetracker.dto.TransactionResponseDto;

import com.team.tracker.financetracker.model.User;
import com.team.tracker.financetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<TransactionResponseDto> createTransaction(@RequestBody CreateTransactionRequestDto request, @AuthenticationPrincipal User principal){
        return ResponseEntity.ok(transactionService.save(request));
    }
}
