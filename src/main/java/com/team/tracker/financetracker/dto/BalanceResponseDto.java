package com.team.tracker.financetracker.dto;

public class BalanceResponseDto {
    private double balance;

    public BalanceResponseDto(double balance) {
        this.balance = balance;
    }

    public double getBalance()  {
        return balance;
    }
}