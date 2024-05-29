package com.example.emsbackend.dto.secondary;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class EventEntityDTO {


    List<PageEntityDTO> pages;

    private Long id;
    private Date gmtCreate;
    private Date gmtModify;
    private String eventName;
    private String eventDesc;
    private String eventType;
    private String eventStatus;
    private Long triggerTimes;
    private String projectInfo;
    private String pageInfo;
    private String creator;
    private Date eventOnlineTime;
    private Date eventOfflineTime;
    private String extendedParameters;
    private String sampleImages;
    private String identifierCode;

}
