package com.example.emsbackend.dto.events;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Data
public class EventEntityDTO {


    // List of parameterIds
    private List<String> parameterIds;

    private List<ParameterEntityDTO> parameterObjs;

    // Key-value pair for parameter, (parameterKey, parameterValue)
    private Map<String, String> parameters; // Used for backend -> frontend
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
    private String id;

}
