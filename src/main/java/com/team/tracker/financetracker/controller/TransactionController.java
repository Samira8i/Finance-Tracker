package com.team.tracker.financetracker.controller;

import com.team.tracker.financetracker.dto.BalanceResponseDto;
import com.team.tracker.financetracker.dto.CreateTransactionRequestDto;
import com.team.tracker.financetracker.dto.MonthlyStatsDto;
import com.team.tracker.financetracker.dto.TransactionResponseDto;

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

    @GetMapping("/history")
    public ResponseEntity<List<TransactionResponseDto>> getUserTransaction(@AuthenticationPrincipal UserDetails userDetails){
        List<TransactionResponseDto> transactions = transactionService.getUserTransaction(userDetails);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/index")
   public ResponseEntity<BalanceResponseDto> getUserBalance(@AuthenticationPrincipal UserDetails userDetails){
        double balance = transactionService.calculateUserTransaction(userDetails);
        return ResponseEntity.ok(new BalanceResponseDto(balance));
    }

    @GetMapping("/last-year-stats")
    public ResponseEntity<MonthlyStatsDto> getLastYearStats(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(transactionService.getLast12MonthsStats(userDetails));
    }
}