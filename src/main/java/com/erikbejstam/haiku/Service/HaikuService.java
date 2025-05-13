package com.erikbejstam.haiku.Service;

import com.erikbejstam.haiku.Model.Haiku;
import com.erikbejstam.haiku.Repository.HaikuRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HaikuService {
    private final HaikuRepository repository;

    @Autowired
    public HaikuService(HaikuRepository repository) {
        this.repository = repository;
    }

    public Haiku findById(Long id) {
        Optional<Haiku> haiku = repository.findById(id);
        return haiku.orElse(null);
    }

    public List<Haiku> findAll() {
        return repository.findAll();
    }

    public Haiku save(Haiku haiku) {
        return repository.save(haiku);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("User with id " + id + "not found.");
        repository.deleteById(id);
    }
}