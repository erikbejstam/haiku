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
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ValidHaiku
    private String text;

    private LocalDateTime timestamp;

    // empty constructor as is required by jpa
    public Haiku() {
    }

    public Haiku(User user, String text) {
        this.user = user;
        this.text = text;
        this.timestamp = LocalDateTime.now();
    }

    // Getters

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    // Setters

    public void setText(String text) {
        this.text = text;
    }

    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public void setUser(User user) { this.user = user; }
}