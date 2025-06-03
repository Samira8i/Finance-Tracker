package com.team.tracker.financetracker.init;

import com.team.tracker.financetracker.enums.CategoryType;
import com.team.tracker.financetracker.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategorySplitter {
    private final List<Category> incomeCategories = new ArrayList<>();
    private final List<Category> expenseCategories = new ArrayList<>();

    public CategorySplitter(List<Category> allCategories) {
        for (Category category : allCategories) {
            if (category.getCategoryType() == CategoryType.INCOME) {
                incomeCategories.add(category);
            } else {
                expenseCategories.add(category);
            }
        }
    }

    public List<Category> getIncomeCategories() {
        return incomeCategories;
    }

    public List<Category> getExpenseCategories() {
        return expenseCategories;
    }
}