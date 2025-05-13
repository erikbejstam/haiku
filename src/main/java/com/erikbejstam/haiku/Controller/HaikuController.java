package com.erikbejstam.haiku.Controller;

import com.erikbejstam.haiku.Model.Haiku;
import com.erikbejstam.haiku.Repository.HaikuRepository;
import com.erikbejstam.haiku.Service.HaikuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/haiku")
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

    @PostMapping
    public Haiku create(@RequestBody Haiku haiku) {
        return service.save(haiku);
    }

    @GetMapping("/test")
    public Haiku test() {
        return new Haiku("erik", "asd", "texish");
    }
}
