package com.example.emsbackend.api.controller;


import com.example.emsbackend.api.dto.UserEntityDTO;
import com.example.emsbackend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        UserEntityDTO userByEmailAndPasswordHash = loginService.findUserByEmailAndPasswordHash(email, password);
        if (userByEmailAndPasswordHash != null) {
            return ResponseEntity.ok("Successfully login");
        }
        return ResponseEntity.notFound().build();
    }



}
