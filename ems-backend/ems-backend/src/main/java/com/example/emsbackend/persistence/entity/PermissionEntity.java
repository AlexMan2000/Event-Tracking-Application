package com.example.emsbackend.persistence.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name="permission")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 75)
    private String title;

    @Column(name = "slug", nullable = false, length = 100)
    private String slug;

    @Column(name = "description", columnDefinition = "TINYTEXT")
    private String description;

    @Column(name = "active")
    private Boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
}
