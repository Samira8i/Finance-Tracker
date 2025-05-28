package com.team.tracker.financetracker.exceptions;

import java.util.UUID;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(UUID categoryId) {
        super("Категория с ID: " + categoryId + " не найдена");
    }
}
