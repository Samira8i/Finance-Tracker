package com.team.tracker.financetracker.init;

import com.team.tracker.financetracker.model.User;
import com.team.tracker.financetracker.repository.CategoryRepository;
import com.team.tracker.financetracker.repository.TransactionRepository;
import com.team.tracker.financetracker.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.UUID;

public class MockDataInitializer implements CommandLineRunner {

    private static final UUID TEST_USER_ID = UUID.fromString("00000000-0000-0000-0000-000000000001");

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionGenerator transactionGenerator;

    public MockDataInitializer(UserRepository userRepository, TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.transactionGenerator = new TransactionGenerator();
    }

    @Override
    public void run(String... args) {

        User user = userRepository.findById(TEST_USER_ID)
                .orElseThrow(() -> new IllegalStateException("Тестовый пользователь не найден! Проверьте data.sql."));

        // Генерируем транзакции, если их ещё нет
        if (transactionRepository.findByUserId(TEST_USER_ID).isEmpty()) {
            transactionGenerator.generateMonthlyTransactions(
                    user,
                    categoryRepository.findAll(),
                    transactionRepository
            );
        }
    }
}
