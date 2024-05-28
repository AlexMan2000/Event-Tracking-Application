package com.example.emsbackend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

//@Builder
@Data
public class DagEntityDTO {

    private String dagId;
    private Boolean isPaused;
    private Boolean isSubdag;
    private Boolean isActive;
    private Date lastSchedulerRun;
    private Date lastPickled;
    private Date lastExpired;
    private Boolean schedulerLock;
    private Integer pickleId;
    private String fileloc;
    private String owners;
    private Long id;

}
