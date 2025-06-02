package com.team.tracker.financetracker.controller;

import com.team.tracker.financetracker.dto.CreateTransactionRequestDto;
import com.team.tracker.financetracker.dto.TransactionResponseDto;

import com.team.tracker.financetracker.model.User;
import com.team.tracker.financetracker.security.CustomUserDetailsService;
import com.team.tracker.financetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<TransactionResponseDto> createTransaction(@RequestBody CreateTransactionRequestDto request, @AuthenticationPrincipal UserDetails principal){
        return ResponseEntity.ok(transactionService.save(request,principal));
    }

    @PostMapping("/history")
    public ResponseEntity<List<TransactionResponseDto>> getUserTransaction(@AuthenticationPrincipal UserDetails userDetails){
        List<TransactionResponseDto> transactions = transactionService.getUserTransaction(userDetails);
        return ResponseEntity.ok(transactions);
    }
}
