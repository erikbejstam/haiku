package com.erikbejstam.haiku.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain secFilterChain(HttpSecurity http) throws Exception { // Marco's WebSecurityConfigurerAdapter is deprecated. This seems to work in basically the same way.
        http
                .authorizeHttpRequests()

    }

}
