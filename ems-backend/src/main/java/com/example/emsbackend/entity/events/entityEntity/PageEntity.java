package com.example.emsbackend.entity.events.entityEntity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "page")
public class PageEntity {
    @org.springframework.data.annotation.Id
    @jakarta.persistence.Id
    @Column(name="id")
    private Long id;

    @Column(name = "gmt_create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gmtCreate;

    @Column(name = "gmt_modify")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gmtModify;

    @Column(name = "page_english_name")
    private String pageEnglishName;

    @Column(name = "page_chinese_name")
    private String pageChineseName;

    @Column(name = "page_desc")
    private String pageDesc;

    @Column(name = "page_type")
    private String pageType;

    @Column(name = "page_status")
    private String pageStatus;

    @Column(name = "client_version")
    private String clientVersion;

    @Column(name = "creator_name")
    private String creatorName;

    @Column(name = "auditor_name")
    private String auditorName;

    @Column(name = "parent_pages")
    private String parentPages;

    @Column(name = "sample_images")
    private String sampleImages;

    @Column(name = "identifier_code")
    private String identifierCode;

    @Column(name = "page_online_time")
    private Date pageOnlineTime;

    @Column(name = "page_offline_time")
    private Date pageOfflineTime;


    /**
     * Module Attributes
     */
    @ManyToMany
    @JoinTable(
            name = "page_event",
            joinColumns = @JoinColumn(name = "page_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<EventEntity> events;

    @ManyToMany
    @JoinTable(
            name = "page_parameter",
            joinColumns = @JoinColumn(name = "page_id"),
            inverseJoinColumns = @JoinColumn(name = "parameter_id")
    )
    private Set<ParameterEntity> parameters;

}
