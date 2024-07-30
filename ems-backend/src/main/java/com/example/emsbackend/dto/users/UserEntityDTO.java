package com.example.emsbackend.dto.users;

import lombok.Data;

import java.util.Date;
@Data
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
