package com.team.tracker.financetracker.repository;

import com.team.tracker.financetracker.enums.CategoryType;
import com.team.tracker.financetracker.model.Category;
import com.team.tracker.financetracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findByCategoryType(CategoryType categoryType);

}