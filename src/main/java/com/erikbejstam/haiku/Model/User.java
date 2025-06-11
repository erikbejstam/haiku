package com.erikbejstam.haiku.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "haikuuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // note: might have to put some annotations on the fields below to set how these fields should "behave" in the db.
    private String username;
    private String password;
    private String email;
    @OneToMany(mappedBy="author")
    private List<Haiku> haikus;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Haiku> getHaikus() {return haikus;}

    public Long getId() { return id; }
}

