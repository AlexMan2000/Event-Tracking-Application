package com.example.emsbackend.config.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

         http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/match"))
                 .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                 .formLogin(Customizer.withDefaults());


    }
}
