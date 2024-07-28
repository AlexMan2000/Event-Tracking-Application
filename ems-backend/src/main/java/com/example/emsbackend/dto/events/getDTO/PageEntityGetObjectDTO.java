package com.example.emsbackend.dto.events.getDTO;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class PageEntityGetObjectDTO {
    private Long id;
    private Date gmtCreate;
    private Date gmtModify;
    private String pageEnglishName;
    private String pageChineseName;
    private String pageDesc;
    private String pageType;
    private String pageStatus;
    private String clientVersion;
    private String creatorName;
    private String auditorName;
    private String parentPages;
    private String sampleImages;
    private String identifierCode;
    private Date pageOnlineTime;
    private Date pageOfflineTime;

    /**
     * Module Attributes
     */
    /**
     * Module Attributes
     */
    private Set<EventEntityGetObjectDTO> events;
    private Set<ParameterEntityGetObjectDTO> parameters;
}
