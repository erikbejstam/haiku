package com.erikbejstam.haiku.Controller;

import com.erikbejstam.haiku.Model.Haiku;
import com.erikbejstam.haiku.Service.HaikuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    HaikuService haikuService;

    @GetMapping("/")
    public String home(Model model) {
        List<Haiku> haikus = haikuService.findAll();

        model.addAttribute("haikus", haikus);

        return "main";
    }
}
