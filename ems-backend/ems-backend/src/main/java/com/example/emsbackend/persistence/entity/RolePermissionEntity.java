package com.example.emsbackend.persistence.entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="role_permission")
public class RolePermissionEntity {

    @EmbeddedId
    private RolePermissionId id;

    @MapsId("roleId")
    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "id", insertable = false, updatable = false)
    private RoleEntity role;

    @MapsId("permissionId")
    @ManyToOne
    @JoinColumn(name = "permissionId", referencedColumnName = "id", insertable = false, updatable = false)
    private PermissionEntity permission;

    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}



@Embeddable
class RolePermissionId implements Serializable {
    @Column(name = "roleId")
    private Long roleId;

    @Column(name = "permissionId")
    private Long permissionId;

    // Constructors, getters, and setters, hashCode and equals implementation
}