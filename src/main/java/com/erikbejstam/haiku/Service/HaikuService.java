package com.erikbejstam.haiku.Service;

import com.erikbejstam.haiku.Model.Haiku;
import com.erikbejstam.haiku.Model.User;
import com.erikbejstam.haiku.Repository.HaikuRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HaikuService {
    private final HaikuRepository repository;
    private final UserService userService;

    @Autowired
    public HaikuService(HaikuRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public Haiku findById(Long id) {
        Optional<Haiku> haiku = repository.findById(id);
        return haiku.orElse(null);
    }

    public List<Haiku> findAll() {
        return repository.findAll();
    }

    public Haiku save(Haiku haiku) {
        System.out.println("Running SAVE with Haiku: " + haiku.getText());
        return repository.save(haiku);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("User with id " + id + "not found.");
        repository.deleteById(id);
    }

    public void processHaiku(Haiku haiku) {
        // first, get user from "logged in context" or w/e
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User currentUser = userService.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // input user details and timestampin haiku
        haiku.setUser(currentUser);
        haiku.setTimestamp(LocalDateTime.now());

        repository.save(haiku);
    }
}