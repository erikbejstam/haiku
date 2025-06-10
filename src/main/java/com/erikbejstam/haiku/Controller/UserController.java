package com.erikbejstam.haiku.Controller;

import com.erikbejstam.haiku.Model.User;
import com.erikbejstam.haiku.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller()
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String user(@PathVariable Long id, Model model) {
        Optional<User> data = userService.findUser(id);

        if (!data.isPresent()) {
            return "main"; // TODO: I have to do something else here later.
        }

        User user = data.get();
        model.addAttribute("user", user);
        return "user";
    }
}
