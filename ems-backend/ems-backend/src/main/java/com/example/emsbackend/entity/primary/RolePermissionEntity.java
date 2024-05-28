package com.example.emsbackend.entity.primary;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="role_permission")
public class RolePermissionEntity {

    @EmbeddedId
    private RolePermissionId id;


    /**
     * Many means multiple records in role_permission table
     * One means one record in role table
     *
     * In other words, each record in role_permission maps to
     * exactly one record in role
     *
     * Each record in role could appear multiple times in role_permission table
     *
     * role_permission -- N to 1 ---> role
     * role --- 1 to N ---> role_permission
    */
    @MapsId("role_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
    private RoleEntity role;

    @MapsId("permission_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PermissionEntity permission;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}

