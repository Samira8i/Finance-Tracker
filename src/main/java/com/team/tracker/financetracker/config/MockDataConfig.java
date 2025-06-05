package com.team.tracker.financetracker.config;

import com.team.tracker.financetracker.init.MockDataInitializer;
import com.team.tracker.financetracker.repository.CategoryRepository;
import com.team.tracker.financetracker.repository.TransactionRepository;
import com.team.tracker.financetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockDataConfig {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public MockDataConfig(UserRepository userRepository,
                          CategoryRepository categoryRepository,
                          TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    @Bean
    public MockDataInitializer mockDataInitializer() {
        return new MockDataInitializer(
                userRepository,
                transactionRepository,
                categoryRepository
        );
    }
}
