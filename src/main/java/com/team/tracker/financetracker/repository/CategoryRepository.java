package com.team.tracker.financetracker.repository;

import com.team.tracker.financetracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}