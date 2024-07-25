package com.example.emsbackend.entity.events.entityEntity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="module")
public class ModuleEntity {
    @jakarta.persistence.Id
    @org.springframework.data.annotation.Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modify")
    private Date gmtModify;

    @Column(name = "module_name")
    private String moduleName;

    @Column(name = "module_identifier")
    private String moduleIdentifier;

    @Column(name = "module_owner")
    private String moduleOwner;

    @Column(name = "module_desc")
    private String moduleDesc;

    @Column(name = "belong_pages")
    private String belongPages;

    @Column(name = "module_status")
    private String moduleStatus;

    @Column(name = "trigger_times")
    private String triggerTimes;

    @Column(name = "sample_images")
    private String sampleImages;

    @Column(name = "module_events")
    private String moduleEvents;

    @Column(name = "creator")
    private String creator;

    @Column(name = "online_date")
    private Date onlineDate;

    @Column(name = "offline_date")
    private Date offlineDate;
}
