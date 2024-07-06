package com.example.emsbackend.entity.events;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "project")
public class ProjectEntity {

    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gmt_create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gmtCreate;

    @Column(name = "gmt_modify")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gmtModify;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_desc")
    private String projectDesc;

    @Column(name = "project_url")
    private String projectUrl;

    @Column(name = "project_status")
    private String projectStatus;

    @Column(name = "project_online_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date projectOnlineTime;

    @Column(name = "project_offline_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date projectOfflineTime;

    @Column(name = "project_manager")
    private String projectManager;

    @Column(name = "product_manager")
    private String productManager;

    @Column(name = "creator")
    private String creator;

    // Getters and Setters
}
