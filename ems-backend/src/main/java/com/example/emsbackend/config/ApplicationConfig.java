package com.example.emsbackend.config;


import com.example.emsbackend.repository.users.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserEntityRepository userEntityRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return userEntityRepository::findUserEntityByEmail;
    }
}
