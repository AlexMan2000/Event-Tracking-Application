package com.example.emsbackend.service.auth;

import com.example.emsbackend.dto.users.UserEntityDTO;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    String generateToken(UserEntityDTO userEntityDTO);

    String generateToken(
            Map<String, Object> claims,
            UserEntityDTO userEntityDTO);


    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);


    Claims extractAllClaims(String token);

    String extractUserEmail(String token);
    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);
}
