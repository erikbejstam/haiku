package com.erikbejstam.haiku.Controller;

import com.erikbejstam.haiku.Model.Haiku;
import com.erikbejstam.haiku.Repository.HaikuRepository;
import com.erikbejstam.haiku.Service.HaikuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/haikus")
public class HaikuController {

    @Autowired
    private final HaikuService service;

    public HaikuController(HaikuService service) {
        this.service = service;
    }

    @GetMapping
    public List<Haiku> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Haiku> get(@PathVariable Long id) {
        Haiku haiku = service.findById(id);
        if (haiku == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND); // This automatically propagates through Spring and sends a 404 response to user.
        return ResponseEntity.ok(haiku);
    }

    @PostMapping
    public ResponseEntity<Haiku> create(@RequestBody @Valid Haiku haiku) {
        Haiku savedHaiku = service.save(haiku);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHaiku);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Haiku haiku = service.findById(id);
        if (haiku == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        service.delete(id);
    }
}

