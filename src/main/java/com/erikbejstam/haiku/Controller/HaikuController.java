package com.erikbejstam.haiku.Controller;

import com.erikbejstam.haiku.Model.Haiku;
import com.erikbejstam.haiku.Service.HaikuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/haikus")
public class HaikuController {

    @Autowired
    private final HaikuService service;

    public HaikuController(HaikuService haikuService) {
        this.service = haikuService;
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

    @PostMapping("/post")
    public String create(@ModelAttribute @Valid Haiku haiku, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/"; // maybe do something else later instead? maybe not
        }

        haiku.setText(normalizeText(haiku.getText()));

        service.processHaiku(haiku);

        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Haiku haiku = service.findById(id);
        if (haiku == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        service.delete(id);
    }

    // Helper functions

    private String normalizeText(String haikuText) {
        if (haikuText == null) return null;
        return haikuText.replace("\r", "");
    }
}

