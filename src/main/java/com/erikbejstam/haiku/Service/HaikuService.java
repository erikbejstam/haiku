package com.erikbejstam.haiku.Service;

import com.erikbejstam.haiku.Model.Haiku;
import com.erikbejstam.haiku.Repository.HaikuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HaikuService {
    private final HaikuRepository repository;

    @Autowired
    public HaikuService(HaikuRepository repository) {
        this.repository = repository;
    }

    public List<Haiku> findAll() {
        return repository.findAll();
    }

    public Haiku save(Haiku haiku) {
        return repository.save(haiku);
    }
}