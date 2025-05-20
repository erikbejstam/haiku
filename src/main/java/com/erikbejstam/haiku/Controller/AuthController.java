package com.erikbejstam.haiku.Controller;

import com.erikbejstam.haiku.Model.User;
import com.erikbejstam.haiku.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    // wrote this in a haste, look up if correct
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest req) {
        User user = authService.register(req);
        UserResponse userResponse = new UserResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}
