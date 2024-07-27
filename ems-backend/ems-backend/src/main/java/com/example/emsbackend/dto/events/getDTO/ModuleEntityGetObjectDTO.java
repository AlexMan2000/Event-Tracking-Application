package com.example.emsbackend.dto.events.getDTO;

import com.example.emsbackend.entity.events.entityEntity.PageEntity;
import com.example.emsbackend.entity.events.entityEntity.ParameterEntity;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class ModuleEntityGetObjectDTO {

    private Long id;
    private Date gmtCreate;
    private Date gmtModify;
    private String moduleName;
    private String identifierCode;
    private String moduleOwner;
    private String moduleDesc;
    private String belongPages;
    private String moduleStatus;
    private String triggerTimes;
    private String sampleImages;
    private String moduleEvents;
    private String creator;
    private Date moduleOnlineTime;
    private Date moduleOfflineTime;
    private Set<PageEntityGetObjectDTO> pages;
    private Set<ParameterEntityGetObjectDTO> parameters;
}
