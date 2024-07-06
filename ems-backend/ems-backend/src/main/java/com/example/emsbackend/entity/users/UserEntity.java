package com.example.emsbackend.entity.users;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
@Data
@Table(name = "user")
public class UserEntity {

    @Id
    // Auto-incremented PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @jakarta.persistence.Id
    @Column(name = "id")
    private Long id;

    @Column(name = "roleId")
    private Long roleId;

    @Column(name = "firstName", length = 50)
    private String firstName;

    @Column(name = "middleName", length = 50)
    private String middleName;

    @Column(name = "lastName", length = 50)
    private String lastName;

    @Column(name = "mobile", length = 15)
    private String mobile;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "passwordHash", length = 32)
    private String passwordHash;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registeredAt")
    private Date registeredAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastLogin")
    private Date lastLogin;

    @Column(name = "intro", columnDefinition = "TINYTEXT")
    private String intro;

    @Column(name = "profile", columnDefinition = "TEXT")
    private String profile;

}
