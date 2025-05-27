package com.team.tracker.financetracker.service;

import com.team.tracker.financetracker.dto.CreateTransactionRequestDto;
import com.team.tracker.financetracker.model.Category;
import com.team.tracker.financetracker.model.Transaction;
import com.team.tracker.financetracker.model.User;
import com.team.tracker.financetracker.repository.CategoryRepository;
import com.team.tracker.financetracker.repository.TransactionRepository;
import com.team.tracker.financetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository, UserRepository userRepository){
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Transaction> getUserTransaction(UUID userId){
        return transactionRepository.findByUserId(userId);
    }

    @Transactional
    public Transaction save(CreateTransactionRequestDto request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User с ID: " + request.getUserId() + " не найден")); //надо заменить...вроде

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Категория с ID: " + request.getCategoryId() + " не найдена"));

        Transaction transaction = new Transaction(request.getAmount(), request.getTransactionType(), category, user);

        return transactionRepository.save(transaction);

    }
}
