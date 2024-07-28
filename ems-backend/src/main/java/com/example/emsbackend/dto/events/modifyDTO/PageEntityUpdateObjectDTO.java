package com.example.emsbackend.dto.events.modifyDTO;

import com.example.emsbackend.entity.events.entityEntity.EventEntity;
import com.example.emsbackend.entity.events.entityEntity.ParameterEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;


@Data
public class PageEntityUpdateObjectDTO {
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
    private Set<Long> eventIds;
    private Set<Long> parameterIds;
}
