package com.example.emsbackend.dto.events.getDTO;

import lombok.Data;

import java.util.Date;
import java.util.Set;


@Data
public class EventEntityGetObjectDTO {
    private String identifierCode;
    private Date gmtCreate;
    private Date gmtModify;
    private String eventName;
    private String eventDesc;
    private String eventType;
    private String eventStatus;
    private Long triggerTimes;
    private String creator;
    private Date eventOnlineTime;
    private Date eventOfflineTime;
    private String sampleImages;
    private Long id;
    Set<ParameterEntityGetObjectDTO> parameters;
}
