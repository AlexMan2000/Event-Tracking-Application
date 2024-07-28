package com.example.emsbackend.criteria_utils.searching;

import lombok.Data;

import java.util.Date;

@Data
public class ModuleEntitySearchCriteria implements SearchCriteria {

    private Long id;
    private Date gmtCreate;
    private Date gmtModify;
    private String moduleName;
    private String moduleIdentifier;
    private String moduleOwner;
    private String moduleDesc;
    private String belongPages;
    private String moduleStatus;
    private String triggerTimes;
    private String sampleImages;
    private String moduleEvents;
    private String creator;
    private Date onlineDate;
    private Date offlineDate;
}
