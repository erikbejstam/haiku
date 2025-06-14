package com.erikbejstam.haiku.Model;

import com.erikbejstam.haiku.Validation.ValidHaiku;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

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

    @NotNull
    @ValidHaiku
    private String text;

    @NotNull
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