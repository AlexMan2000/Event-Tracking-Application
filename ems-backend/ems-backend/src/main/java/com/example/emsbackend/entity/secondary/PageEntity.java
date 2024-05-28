package com.example.emsbackend.entity.secondary;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Entity
@Table(name = "page")
public class PageEntity {

    @Id
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
