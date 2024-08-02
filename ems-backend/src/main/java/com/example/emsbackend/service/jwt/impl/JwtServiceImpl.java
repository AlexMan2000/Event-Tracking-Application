package com.example.emsbackend.service.jwt.impl;

import com.example.emsbackend.dto.users.UserEntityDTO;
import com.example.emsbackend.entity.users.UserEntity;
import com.example.emsbackend.service.jwt.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;


@Service
public class JwtServiceImpl implements JwtService {

    // test_code
    private final String SECRET_KEY = "6c25b0265fc69a60697e59e8618cbdb65b99cedf6f9b2b02abe537c29ad0dc49";
    private final long timeMillis = 1000*60*60*24; // One day token


    public String generateToken(UserEntityDTO userEntityDTO) {
        return generateToken(new HashMap<>(), userEntityDTO);
    }

    public String generateToken(UserEntity userEntity) {
        return generateToken(new HashMap<>(), userEntity);
    }


    public String generateToken(
            Map<String, Object> claims,
            UserEntityDTO userEntityDTO) {
        // JWT的组成(三部分)
        // 1. Header {"type"; 'JWT", "alg": "HS256"} 表示加密的算法
        // 2. Payload {"sub": "1234567890", "name":"john", "admin":true} 存放需要加密的信息，通常是关于用户的
        // 3. Signature(Can only be signed by the server's private key):
        // encodedString = Signature base64UrlEncode(header) + '.' + base64UrlEncode(payload)
        // signature = HMACSFA256(encodedString(), 'secret');
        // 将加密之后的header和payload通过'.'进行拼接


        JwtBuilder jwtBuilder = Jwts.builder();

        String jwtToken = jwtBuilder
                // header
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                // payload
                .setClaims(claims)
                .setSubject(userEntityDTO.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + timeMillis)) // 设置有效期为一天
                .setId(UUID.randomUUID().toString())
                // Signature, using symmetric encryption
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();

        // The token is concatenated by the base64encoded header, payload and signature
        return jwtToken;
    }


    public String generateToken(
            Map<String, Object> claims,
            UserEntity userEntity) {
        // JWT的组成(三部分)
        // 1. Header {"type"; 'JWT", "alg": "HS256"} 表示加密的算法
        // 2. Payload {"sub": "1234567890", "name":"john", "admin":true} 存放需要加密的信息，通常是关于用户的
        // 3. Signature(Can only be signed by the server's private key):
        // encodedString = Signature base64UrlEncode(header) + '.' + base64UrlEncode(payload)
        // signature = HMACSFA256(encodedString(), 'secret');
        // 将加密之后的header和payload通过'.'进行拼接


        JwtBuilder jwtBuilder = Jwts.builder();

        String jwtToken = jwtBuilder
                // header
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                // payload
                .setClaims(claims)
                .setSubject(userEntity.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + timeMillis)) // 设置有效期为一天
                .setId(UUID.randomUUID().toString())
                // Signature, using asymmetric encryption
                .signWith(getSecretKey(),SignatureAlgorithm.HS256)
                .compact();

        // The token is concatenated by the base64encoded header, payload and signature
        return jwtToken;
    }



    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Get 256 bit secret key for HMAC/RS algorithm for signature
      * @return
     */
    private Key getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userEmail = extractUserEmail(token);
        return userEmail.equals(userDetails.getUsername()) && ! isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


}
