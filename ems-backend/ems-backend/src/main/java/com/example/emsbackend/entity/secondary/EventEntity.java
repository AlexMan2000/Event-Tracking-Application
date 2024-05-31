package com.example.emsbackend.entity.secondary;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Entity
@Data
@Table(name = "event", schema = "event_management")
public class EventEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "gmt_create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gmtCreate;
    @Column(name = "gmt_modify")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gmtModify;
    @Column(name = "event_name")
    private String eventName;
    @Column(name = "event_desc")
    private String eventDesc;
    @Column(name = "event_type")
    private String eventType;
    @Column(name = "event_status")
    private String eventStatus;
    @Column(name = "trigger_times")
    private Long triggerTimes;
    @Column(name = "creator")
    private String creator;
    @Column(name = "event_online_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventOnlineTime;
    @Column(name = "event_offline_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventOfflineTime;
    @Column(name = "sample_images")
    private String sampleImages;
    @Id
    @Column(name = "identifier_code")
    private String identifierCode;

    // Getters and Setters
}
