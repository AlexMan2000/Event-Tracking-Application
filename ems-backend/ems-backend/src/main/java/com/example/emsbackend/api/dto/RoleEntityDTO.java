package com.example.emsbackend.api.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.sql.Date;

public class RoleEntityDTO {
    private Long id;
    private String title;
    private String slug;
    private String description;
    private Boolean active;
    private Date createdAt;
    private Date updatedAt;
    private String content;
}
