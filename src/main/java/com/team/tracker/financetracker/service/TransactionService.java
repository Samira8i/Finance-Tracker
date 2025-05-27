package com.team.tracker.financetracker.service;

import com.team.tracker.financetracker.dto.CreateTransactionRequestDto;
import com.team.tracker.financetracker.model.Category;
import com.team.tracker.financetracker.model.Transaction;
import com.team.tracker.financetracker.model.User;
import com.team.tracker.financetracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    // Дефолтные ID для заглушек
    private static final UUID DEFAULT_USER_ID = UUID.fromString("00000000-0000-0000-0000-000000000001");
    private static final UUID DEFAULT_CATEGORY_ID = UUID.fromString("00000000-0000-0000-0000-000000000002");

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CategoryService categoryService, UserService userService){
        this.transactionRepository = transactionRepository;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    public List<Transaction> getUserTransaction(UUID userId){
        return transactionRepository.findByUserId(userId);
    }

    @Transactional
    public Transaction save(CreateTransactionRequestDto request) {

        UUID userId = request.getUserId() != null ? request.getUserId() : DEFAULT_USER_ID; //моковые заглушки
        User user = userService.findById(userId);

        UUID categoryId = request.getCategoryId() != null ? request.getCategoryId() : DEFAULT_CATEGORY_ID; //моковые заглушки
        Category category = categoryService.findById(categoryId);

        Transaction transaction = new Transaction(request.getAmount(), request.getTransactionType(), category, user);

        return transactionRepository.save(transaction);
    }
}
