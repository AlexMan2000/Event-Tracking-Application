package com.example.emsbackend.dto.primary;

import lombok.Data;

import java.sql.Date;
@Data
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
