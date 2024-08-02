package com.example.emsbackend.service.jwt.impl;

import com.example.emsbackend.dto.auths.*;
import com.example.emsbackend.dto.users.UserEntityDTO;
import com.example.emsbackend.entity.users.UserEntity;
import com.example.emsbackend.repository.users.UserEntityRepository;
import com.example.emsbackend.service.jwt.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .lastName(request.getMiddleName())
                .intro(request.getIntro())
                .profile(request.getProfile())
                .roleId(3L)
                .build();

        Date nowTime = new Date();

        try {
            user.setRegisteredAt(nowTime);
            userEntityRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        var jwtToken = jwtServiceImpl.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Date nowTime = new Date();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var user = userEntityRepository.findUserEntityByEmail(request.getEmail());
        user.setLastLogin(nowTime);
        userEntityRepository.save(user);
        var userDTO = this.modelMapper.map(user, UserEntityDTO.class);
        var jwtToken = jwtServiceImpl.generateToken(user);
        return LoginResponse.builder()
                .token(jwtToken)
                .userInfo(userDTO)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException {
        var token = request.getToken();
        String email = jwtServiceImpl.extractUserEmail(token);
        UserEntity userEntityByEmail = userEntityRepository.findUserEntityByEmail(email);
        return AuthenticationResponse.builder()
                .token(token)
                .userInfo(this.modelMapper.map(userEntityByEmail, UserEntityDTO.class))
                .build();
    }


    @Override
    public AuthenticationResponse logout(AuthenticationRequest request) throws AuthenticationException {
        var token = request.getToken();
        String email = jwtServiceImpl.extractUserEmail(token);
        UserEntity userEntityByEmail = userEntityRepository.findUserEntityByEmail(email);
        return AuthenticationResponse.builder()
                .token(token)
                .userInfo(this.modelMapper.map(userEntityByEmail, UserEntityDTO.class))
                .build();
    }
}
