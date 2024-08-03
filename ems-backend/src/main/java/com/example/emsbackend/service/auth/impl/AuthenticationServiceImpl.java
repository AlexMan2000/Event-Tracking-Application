package com.example.emsbackend.service.auth.impl;

import com.example.emsbackend.dto.auths.*;
import com.example.emsbackend.dto.users.UserEntityDTO;
import com.example.emsbackend.entity.users.UserEntity;
import com.example.emsbackend.repository.users.UserEntityRepository;
import com.example.emsbackend.service.auth.AuthenticationService;
import com.example.emsbackend.service.photos.ImageService;
import com.example.emsbackend.service.photos.UploadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    @Autowired
    private UploadService uploadService;

    @Autowired
    private ImageService imageService;


    /**
     * Register the user into the system, user role default to be VIEWER
     * @param request
     * @return
     */
    @Override
    public RegisterResponse register(RegisterRequest request) {

        // 1. Search through the redis database the image indexed by request.profileImageId
        ProfileImageMetadata profileImageMetadata = request.getProfileImageMetadata();
        String cacheImageId = profileImageMetadata.getImageId();
        String mongoImageId = null;
        if (cacheImageId != null) {
            byte[] imageBytes = uploadService.getImageFromTempStorage(cacheImageId);
            try {
                mongoImageId = imageService.saveImage(imageBytes, profileImageMetadata);
            } catch (IOException e) {
                System.out.println("Image cannot be inserted");
                e.printStackTrace();
                return null;
            }
        }


        // 2. Insert user into mysql DB
        var user = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .lastName(request.getMiddleName())
                .intro(request.getIntro())
                .profile(request.getProfile())
                .roleId(3L)
                .profileImageId(mongoImageId)
                .build();

        Date nowTime = new Date();


        try {
            user.setRegisteredAt(nowTime);
            userEntityRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("User cannot be inserted");
            return null;
        }

        return RegisterResponse.builder()
                .build();
    }

    /**
     * Used to log user in
     * @param request
     * @return
     */
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

        // Search for the profile Image Id

        var userDTO = this.modelMapper.map(user, UserEntityDTO.class);
        var jwtToken = jwtServiceImpl.generateToken(user);
        return LoginResponse.builder()
                .token(jwtToken)
                .userInfo(userDTO)
                .build();
    }

    /**
     * Authenticate user's, if the user's token is valid, keep user logged in.
     * @param request The user request to prevent repeated logging within token expiration period
     * @return An HttpResponse that contains token information and user information
     * @throws AuthenticationException
     */
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


    /**
     * Use to log out the user, unused(since we log user out by deleting the token at client side)
     * @param request
     * @return
     * @throws AuthenticationException
     */
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

    @Override
    public ValidationResponse checkEmail(ValidationRequest request) {
        String email = request.getEmail();
        UserEntity userEntityByEmail = userEntityRepository.findUserEntityByEmail(email);
        System.out.println(userEntityByEmail);
        if (userEntityByEmail != null) {
            return ValidationResponse.builder()
                    .okToAdd(false).build();
        }
        return ValidationResponse.builder()
                .okToAdd(true).build();
    }
}
