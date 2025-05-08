package com.team.tracker.financetracker.enums;

public enum CategoryType {

    INCOME ("Доход"),//можно добавить цвет
    EXPENSE ("Расход");

    private String typeName;

    private CategoryType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
//можно оставить так, не знаю как будет лучше для отображения
//public enum CategoryType {
//    INCOME, EXPENSE
//}



