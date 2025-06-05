package com.team.tracker.financetracker.service;

import com.team.tracker.financetracker.dto.BalanceResponseDto;
import com.team.tracker.financetracker.dto.CreateTransactionRequestDto;
import com.team.tracker.financetracker.dto.MonthlyStatsDto;
import com.team.tracker.financetracker.dto.TransactionResponseDto;
import com.team.tracker.financetracker.enums.TransactionType;
import com.team.tracker.financetracker.model.Category;
import com.team.tracker.financetracker.model.Transaction;
import com.team.tracker.financetracker.model.User;
import com.team.tracker.financetracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    // Дефолтные ID для заглушек
    private static final UUID DEFAULT_CATEGORY_ID = UUID.fromString("00000000-0000-0000-0000-000000000002");
    private static final DateTimeFormatter MONTH_FORMATTER =
            DateTimeFormatter.ofPattern("MM", new Locale("ru")); //здесь менять формат вывода

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CategoryService categoryService, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    public double calculateUserTransaction(UserDetails userDetails) {
        UUID userId = userService.findByUsername(userDetails.getUsername()).getId();
        return transactionRepository.sumIncomeExpense(userId);

    }

    public List<TransactionResponseDto> getUserTransaction(UserDetails userDetails) {
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

        User user = userService.findByUsername(userDetails.getUsername());

        UUID categoryId = request.getCategoryId() != null ? request.getCategoryId() : DEFAULT_CATEGORY_ID; //моковые заглушки
        Category category = categoryService.findById(categoryId);

        Transaction transaction = new Transaction(request.getAmount(), request.getTransactionType(), category, user);

        transactionRepository.save(transaction);
        return TransactionResponseDto.responseFromTransaction(transaction);
    }

    @Transactional
    public MonthlyStatsDto getLast12MonthsStats(UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());

        YearMonth currentMonth = YearMonth.now();
        List<YearMonth> months = IntStream.rangeClosed(0, 11)
                .mapToObj(i -> currentMonth.minusMonths(i))
                .sorted()
                .collect(Collectors.toList());

        List<String> monthNames = months.stream()
                .map(month -> month.format(MONTH_FORMATTER))
                .collect(Collectors.toList());

        List<Double> incomes = new ArrayList<>();
        List<Double> expenses = new ArrayList<>();

        for (YearMonth month : months) {
            incomes.add(transactionRepository.getTotalIncomeForMonth(
                    user.getId(), month.getYear(), month.getMonthValue()));
            expenses.add(transactionRepository.getTotalExpensesForMonth(
                    user.getId(), month.getYear(), month.getMonthValue()));
        }

        return new MonthlyStatsDto(monthNames, incomes, expenses);
    }
}