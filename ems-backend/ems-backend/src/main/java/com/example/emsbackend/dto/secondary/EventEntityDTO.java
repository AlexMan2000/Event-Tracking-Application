package com.example.emsbackend.dto.secondary;
import lombok.Data;

import java.util.Date;
import java.util.Map;


@Data
public class EventEntityDTO {

    private Map<String, String> parameters; // Used for backend -> frontend
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
    private String identifierCode;

}
