package com.example.emsbackend.controller;


import com.example.emsbackend.dto.UserEntityDTO;
import com.example.emsbackend.service.LoginService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    private final static long timeMillis = 1000*60*60*24;
    private String symmetricKey = "admin";


    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }



    @GetMapping("/user")
    public ResponseEntity<String> validateUserLogin(@RequestParam(name="email", required = false, defaultValue = "john.doe@example.com") String email
            , @RequestParam(name="passwordHash", required = false, defaultValue = "482c811da5d5b4bc6d497ffa98491e38") String password) {


        // 校验用户
        UserEntityDTO userByEmailAndPasswordHash = loginService.findUserByEmailAndPasswordHash(email, password);
        if (userByEmailAndPasswordHash == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        if (!userByEmailAndPasswordHash.getPasswordHash().equals(password)) {
            return new ResponseEntity<>("Password Mismatch", HttpStatus.NOT_ACCEPTABLE);
        }

        // 登录成功将Token返回给用户
        return new ResponseEntity<>("Login successfully", HttpStatus.OK);

    }


    public String generateToken(UserEntityDTO userEntityDTO) {
        // JWT的组成(三部分)
        // 1. Header {"type"; 'JWT", "alg": "HS256"} 表示加密的算法
        // 2. Payload {"sub": "1234567890", "name":"john", "admin":true} 存放需要加密的信息，通常是关于用户的
        // 3. Signature:
        // encodedString = Signature base64UrlEncode(header) + '.' + base64UrlEncode(payload)
        // signature = HMACSFA256(encodedString(), 'secret');
        // 将加密之后的header和payload通过'.'进行拼接


        JwtBuilder jwtBuilder = Jwts.builder();

        String jwtToken = jwtBuilder
                // header
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                // payload
                .claim("email",userEntityDTO.getEmail())
                .claim("passwordHash", userEntityDTO.getPasswordHash())
                .claim("registeredAT", userEntityDTO.getRegisteredAt())
                .setExpiration(new Date(System.currentTimeMillis() + timeMillis)) // 设置有效期为一天
                .setId(UUID.randomUUID().toString())
                // Signature,
                .signWith(SignatureAlgorithm.HS256, symmetricKey)
                .compact();

        // The token is concatenated by the base64encoded header, payload and signature
        return jwtToken;
    }


    public void parseToken(String token) {
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(symmetricKey).parseClaimsJws(token);
        Claims body = claimsJws.getBody();

    }




}
