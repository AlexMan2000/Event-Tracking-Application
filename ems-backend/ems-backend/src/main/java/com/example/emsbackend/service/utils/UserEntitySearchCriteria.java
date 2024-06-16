package com.example.emsbackend.service.utils;

import lombok.Data;

import java.sql.Date;

@Data
public class UserEntitySearchCriteria {
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
