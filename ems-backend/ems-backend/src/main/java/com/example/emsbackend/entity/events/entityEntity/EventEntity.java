package com.example.emsbackend.entity.events.entityEntity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "event", schema = "event-management")
public class EventEntity {

    @org.springframework.data.annotation.Id
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;  // 事件编码

    @Column(name="identifier_code")
    private String identifierCode;
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
    /**
     * Module Attributes
     */
    @ManyToMany
    @JoinTable(
            name = "event_parameter",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "parameter_id")
    )
    private Set<ParameterEntity> parameters;
}
