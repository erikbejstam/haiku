package com.erikbejstam.haiku.Controller;

import com.erikbejstam.haiku.Model.Haiku;
import com.erikbejstam.haiku.Repository.HaikuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/haiku")
public class HaikuController {

    @Autowired
    private HaikuRepository repository;

    @GetMapping
    public List<Haiku> getAll() {
        return repository.findAll();
    }
}
