package com.example.emsbackend.entity.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.sql.Date;


@Data
@Entity
@Table(name="dagentity")
public class DagEntity implements Serializable {

    @Id
    @Column(name = "dag_id")
    private String dagId;

    @Column(name = "is_paused")
    private Boolean isPaused;

    @Column(name = "is_subdag")
    private Boolean isSubdag;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "last_scheduler_run")
    private Date lastSchedulerRun;

    @Column(name = "last_pickled")
    private Date lastPickled;

    @Column(name = "last_expired")
    private Date lastExpired;

    @Column(name = "scheduler_lock")
    private Boolean schedulerLock;

    @Column(name = "pickle_id")
    private Integer pickleId;

    @Column(name = "fileloc")
    private String fileloc;

    @Column(name = "owners")
    private String owners;

    @jakarta.persistence.Id
    private Long id;

}