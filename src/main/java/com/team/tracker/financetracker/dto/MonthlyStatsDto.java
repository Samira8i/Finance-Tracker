package com.team.tracker.financetracker.dto;

import java.util.List;

public class MonthlyStatsDto {

    private List<String> months; // Формат: "Январь 2023"
    private List<Double> incomes; // Доходы по месяцам
    private List<Double> expenses; // Расходы по месяцам

    public MonthlyStatsDto(List<String> months,
                           List<Double> incomes,
                           List<Double> expenses) {
        this.months = months;
        this.incomes = incomes;
        this.expenses = expenses;
    }

    public List<String> getMonths() {
        return months;
    }

    public void setMonths(List<String> months) {
        this.months = months;
    }

    public List<Double> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Double> incomes) {
        this.incomes = incomes;
    }

    public List<Double> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Double> expenses) {
        this.expenses = expenses;
    }
}