package com.erikbejstam.haiku.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    // empty constructor as is required by jpa
    public Haiku() {}

    public Haiku(String authorName, String title, String text) {
        this.authorName = authorName;
        this.title = title;
        this.text = text;
        this.createdAt = LocalDateTime.now();
    }

    // want to be able to get all information about the object.
    public Long getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Does not have to be able to "set"/update everything. Will maybe change later.
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }


}
