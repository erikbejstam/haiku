package com.erikbejstam.haiku.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Haiku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @Column(length = 100, nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // empty constructor as is required by jpa
    public Haiku() {
    }

    public Haiku(User author, String text) {
        this.author = author;
        this.text = text;
        this.createdAt = LocalDateTime.now();
    }

    // Getters

    public User getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }
}