package com.erikbejstam.haiku.Service;

import com.erikbejstam.haiku.Model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class HaikuUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public HaikuUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found")); // Note: .orElseThrow returns the User if there is one, otherwise throws error. That is why the LH side doesn't have Optional<User>

        return withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}
