package com.team.tracker.financetracker.service;

import com.team.tracker.financetracker.enums.CategoryType;
import com.team.tracker.financetracker.model.Category;
import com.team.tracker.financetracker.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategoriesByType(CategoryType categoryType) {
        return categoryRepository.findByCategoryType(categoryType);
    }
}
