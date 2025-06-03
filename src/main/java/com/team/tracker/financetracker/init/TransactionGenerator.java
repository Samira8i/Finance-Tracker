package com.team.tracker.financetracker.init;

import com.team.tracker.financetracker.enums.TransactionType;
import com.team.tracker.financetracker.model.Category;
import com.team.tracker.financetracker.model.User;
import com.team.tracker.financetracker.model.Transaction;
import com.team.tracker.financetracker.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Random;

public class TransactionGenerator {

    private static final int TRANSACTIONS_PER_TYPE = 10;
    private static final int MIN_AMOUNT = 100;
    private static final int MAX_AMOUNT = 10000;
    private static final int MONTHS_BACK = 12;

    public void generateMonthlyTransactions(User user, List<Category> allCategories, TransactionRepository transactionRepository) {

        CategorySplitter splitter = new CategorySplitter(allCategories);
        Random random = new Random();
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < MONTHS_BACK; i++) {
            YearMonth yearMonth = YearMonth.from(now.minusMonths(i));

            generateTransactions(
                    user,
                    splitter.getIncomeCategories(),
                    TransactionType.INCOME,
                    transactionRepository,
                    random,
                    yearMonth
            );

            generateTransactions(
                    user,
                    splitter.getExpenseCategories(),
                    TransactionType.EXPENSE,
                    transactionRepository,
                    random,
                    yearMonth
            );
        }
    }

    private void generateTransactions(User user, List<Category> categories, TransactionType type, TransactionRepository repository, Random random, YearMonth yearMonth) {

        if (categories.isEmpty()) return;

        for (int i = 0; i < TRANSACTIONS_PER_TYPE; i++) {
            Transaction transaction = createTransaction(
                    user,
                    categories.get(random.nextInt(categories.size())),
                    type,
                    random,
                    yearMonth
            );
            repository.save(transaction);
        }
    }

    private Transaction createTransaction(User user, Category category, TransactionType type, Random random, YearMonth yearMonth) {
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setCategory(category);
        transaction.setAmount(MIN_AMOUNT + random.nextInt(MAX_AMOUNT - MIN_AMOUNT));
        transaction.setTransactionType(type);
        transaction.setTransactionTime(getRandomDateInMonth(yearMonth, random));
        return transaction;
    }

    private LocalDateTime getRandomDateInMonth(YearMonth yearMonth, Random random) {
        int dayOfMonth = 1 + random.nextInt(yearMonth.lengthOfMonth());
        return LocalDateTime.of(
                yearMonth.getYear(),
                yearMonth.getMonth(),
                dayOfMonth,
                random.nextInt(24),
                random.nextInt(60)
        );
    }
}
