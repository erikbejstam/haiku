package com.erikbejstam.haiku.Service;

import com.erikbejstam.haiku.Controller.LoginRequest;
import com.erikbejstam.haiku.Controller.RegisterRequest;
import com.erikbejstam.haiku.Model.User;
import com.erikbejstam.haiku.Repository.UserRepository;
import com.erikbejstam.haiku.Security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JWTUtil jwtUtil;

    public User register(RegisterRequest req) {
        User user = new User();
        user.setUsername(req.username);
        user.setPassword(passwordEncoder.encode(req.password));
        user.setEmail(req.email);
        userRepository.save(user);
        return user;
    }

    public String login(LoginRequest req) {
        User user = userRepository.findByUsername(req.username)
                .orElseThrow(() -> new UsernameNotFoundException("invalid credentials"));

        if (!passwordEncoder.matches(req.password, user.getPassword())) {
            throw new BadCredentialsException("invalid credentials");
        }

        return jwtUtil.generate(user.getUsername());
    }
}
