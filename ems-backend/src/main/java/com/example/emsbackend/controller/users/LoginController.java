package com.example.emsbackend.controller.users;


import com.example.emsbackend.dto.users.UserEntityDTO;
import com.example.emsbackend.service.users.LoginService;
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






}
