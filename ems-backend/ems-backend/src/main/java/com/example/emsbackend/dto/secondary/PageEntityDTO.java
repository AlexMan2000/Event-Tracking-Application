package com.example.emsbackend.dto.secondary;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PageEntityDTO {

    private List<EventEntityDTO> events;
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
}
