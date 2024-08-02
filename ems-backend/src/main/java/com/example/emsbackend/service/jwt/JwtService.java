package com.example.emsbackend.service.jwt;

import com.example.emsbackend.dto.users.UserEntityDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
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
