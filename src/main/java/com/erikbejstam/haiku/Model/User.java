package com.erikbejstam.haiku.Model;

import jakarta.persistence.*;

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

    public User(String username, String password, String email) {
        username = username;
        password = password;
        email = email;
    }

    public User() {}

    public String getUsername() {
        return username;
    }
}
