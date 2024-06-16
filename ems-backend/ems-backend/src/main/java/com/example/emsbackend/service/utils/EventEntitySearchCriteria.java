package com.example.emsbackend.service.utils;

import lombok.Data;

import java.util.Date;


@Data
public class EventEntitySearchCriteria {
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
