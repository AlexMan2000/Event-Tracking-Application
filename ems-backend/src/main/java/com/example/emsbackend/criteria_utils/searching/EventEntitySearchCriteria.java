package com.example.emsbackend.criteria_utils.searching;

import com.example.emsbackend.criteria_utils.searching.SearchCriteria;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
public class EventEntitySearchCriteria implements SearchCriteria {
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
        private Long id;
}
