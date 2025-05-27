package com.team.tracker.financetracker.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(UUID userId) {
        super("Пользователь с ID: " + userId + " не найден");
    }

    public UserNotFoundException(String name) {
        super("Пользователь с именем: " + name + " не найден");
    }
}