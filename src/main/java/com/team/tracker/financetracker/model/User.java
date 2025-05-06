package com.team.tracker.financetracker.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "date")
    private LocalDate creationDate;

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
        setCreationDate(LocalDate.now());

    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

