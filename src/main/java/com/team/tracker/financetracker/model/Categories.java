package com.team.tracker.financetracker.model;

import com.team.tracker.financetracker.enums.CategoryType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "Categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "color", nullable = false)
    //@Pattern(regexp = "^#[0-9A-Fa-f]{6}$") по идее не нужно т.к. сами данные задаем
    private String color;

    @Column(name = "categoryType")
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    public Categories(String name, String color, CategoryType categoryType) {
        this.name = name;
        this.color = color;
        this.categoryType = categoryType;
    }
}
