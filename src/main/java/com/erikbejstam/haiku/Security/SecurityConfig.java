package com.erikbejstam.haiku.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // Marco's WebSecurityConfigurerAdapter is deprecated. This seems to work in basically the same way.
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers( "/css/**", "/jss/**", "/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) // TODO: Check out what to do with CSRF. Not really understading rn. But this is for enabling h2.
                .headers(headers -> headers // TODO: Check this out aswell. This seems to also be needed for H2.
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());

        return http.build();
    }

    // This is just for testing/dev.
    // You need a UserDetailsService, which is like the API to your db, tho in this case it actually kind of IS the object holding your data, since it's just an in-memory storage thing.
    // So this bean runs on startup, creating a user and putting it into the storaguserServicee.
    // Then you have a UserDetailsService in your app that Spring can you use. And it does that when someone tries to log in, it will use a function in the
    // InMemoryUserDetailsManager that loads a specific user, so it can authenticate the login-data.
    // Btw, UserService and UserDetailsService are different things. The first is where you more manually write your function for interacting with db, and the second
    // is only used by Spring (it's a Spring interface) during login to load user from db.
    @Bean
    public UserDetailsService users() { // TODO: Ok so rn this isn't accessing my H2 DB, fix that.
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }

}
