package com.erikbejstam.haiku.Security;

import com.erikbejstam.haiku.Service.HaikuUserDetailsService;
import com.erikbejstam.haiku.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // Marco's WebSecurityConfigurerAdapter is deprecated. This seems to work in basically the same way.
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers( "/css/**", "/jss/**", "/h2-console/**", "/").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) // TODO: Check out what to do with CSRF. Not really understading rn. But this is for enabling h2.
                .headers(headers -> headers // TODO: Check this out aswell. This seems to also be needed for H2.
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());

        return http.build();
    }

    public UserDetailsService HaikuUserDetailsService(UserService userService) {
        return HaikuUserDetailsService(userService);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
