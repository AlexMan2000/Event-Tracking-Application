package com.example.emsbackend.api.dto;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.sql.Date;

public class UserEntityDTO {
    private Long id;
    private Long roleId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobile;
    private String email;
    private String passwordHash;
    private Date registeredAt;
    private Date lastLogin;
    private String intro;
    private String profile;
}
