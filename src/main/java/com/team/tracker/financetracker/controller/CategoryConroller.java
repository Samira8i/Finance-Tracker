package com.team.tracker.financetracker.controller;

import com.team.tracker.financetracker.enums.CategoryType;
import com.team.tracker.financetracker.model.Category;
import com.team.tracker.financetracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:63342")
public class CategoryConroller {

    private final CategoryService categoryService;

    @Autowired
    public CategoryConroller(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories") // Только конечная часть пути
    public List<Category> getCategoriesByType(@RequestParam CategoryType categoryType) {
        return categoryService.getCategoriesByType(categoryType);
    }

}