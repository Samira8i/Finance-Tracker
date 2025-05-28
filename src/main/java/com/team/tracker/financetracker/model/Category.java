package com.team.tracker.financetracker.model;

import com.team.tracker.financetracker.enums.CategoryType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "color", unique = true, nullable = false)
    //@Pattern(regexp = "^#[0-9A-Fa-f]{6}$") по идее не нужно т.к. сами данные задаем
    private String color;

    @Column(name = "category_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    public Category(String name, String color, CategoryType categoryType) {
        this.name = name;
        this.color = color;
        this.categoryType = categoryType;
    }

    public Category() {}

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String colorHex) {
        this.color = color;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }
}
