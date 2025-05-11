package com.erikbejstam.haiku.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// The haiku should have
// name, id, creator, text

@Entity
public class Haiku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authorName;

    @Column(length=50, nullable = false)
    private String title;

    @Column(length=100, nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Haiku(String authorName, String title, String text) {
        this.authorName = authorName;
        this.title = title;
        this.text = text;
    }
}
