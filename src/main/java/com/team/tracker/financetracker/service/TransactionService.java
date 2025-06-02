package com.team.tracker.financetracker.service;

import com.team.tracker.financetracker.dto.CreateTransactionRequestDto;
import com.team.tracker.financetracker.dto.TransactionResponseDto;
import com.team.tracker.financetracker.model.Category;
import com.team.tracker.financetracker.model.Transaction;
import com.team.tracker.financetracker.model.User;
import com.team.tracker.financetracker.repository.TransactionRepository;
import com.team.tracker.financetracker.security.CustomUserDetailsService;
import org.hibernate.mapping.UserDefinedArrayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<TransactionResponseDto> getUserTransaction(UserDetails userDetails){
        UUID userId = userService.findByUsername(userDetails.getUsername()).getId();
        List<Transaction> transactions = transactionRepository.findByUserIdOrderByTransactionTimeDesc(userId);
        List<TransactionResponseDto> result = new ArrayList<>(transactions.size());
        for (Transaction transaction : transactions) {
            result.add(TransactionResponseDto.responseFromTransaction(transaction));
        }
        return result;
    }

    @Transactional
    public TransactionResponseDto save(CreateTransactionRequestDto request, UserDetails userDetails) {

        //UUID userId = request.getUserId() != null ? request.getUserId() : DEFAULT_USER_ID; //моковые заглушки
        //user = userService.findById(userId); по факту уже ненадо Дима сам ес чо удалит.
        User user = userService.findByUsername(userDetails.getUsername());

        UUID categoryId = request.getCategoryId() != null ? request.getCategoryId() : DEFAULT_CATEGORY_ID; //моковые заглушки
        Category category = categoryService.findById(categoryId);

        Transaction transaction = new Transaction(request.getAmount(), request.getTransactionType(), category, user);

        transactionRepository.save(transaction);
        return TransactionResponseDto.responseFromTransaction(transaction);
    }
}
