package com.team.tracker.financetracker.service;

import com.team.tracker.financetracker.enums.CategoryType;
import com.team.tracker.financetracker.exceptions.CategoryNotFoundException;
import com.team.tracker.financetracker.model.Category;
import com.team.tracker.financetracker.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategoriesByType(CategoryType categoryType) {
        return categoryRepository.findByCategoryType(categoryType);
    }

    public Category findById(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }
}
