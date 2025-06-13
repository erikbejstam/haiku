package com.erikbejstam.haiku.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="haikus")
public class Haiku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // empty constructor as is required by jpa
    public Haiku() {
    }

    public Haiku(User author, String text) {
        this.author = author;
        this.text = text;
        this.timestamp = LocalDateTime.now();
    }

    // Getters

    public User getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }
}