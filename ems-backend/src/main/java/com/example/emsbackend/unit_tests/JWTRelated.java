package com.example.emsbackend.unit_tests;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

public class JWTRelated {

    private String symmetricKey = "admin";
    private final static long timeMillis = 1000*60*60*24;

    @Test
    public void testJWT() {
        JwtBuilder jwtBuilder = Jwts.builder();

        String jwtToken = jwtBuilder
                // header
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                // payload
                .claim("email","xixi")
                .claim("passwordHash", "haha")
                .claim("registeredAT", "pipi")
                .setExpiration(new Date(System.currentTimeMillis() + timeMillis)) // 设置有效期为一天
                .setId(UUID.randomUUID().toString())
                // Signature,
                .signWith(SignatureAlgorithm.HS256, symmetricKey)
                .compact();

        // The
        System.out.println(jwtToken);
    }
}
