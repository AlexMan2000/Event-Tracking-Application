package com.example.emsbackend.dto.events.getDTO;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class ProjectEntityGetObjectDTO {

    /**
     * Entity attributes
     */
    private Long id;
    private Date gmtCreate;
    private Date gmtModify;
    private String projectName;
    private String projectDesc;
    private String projectUrl;
    private String projectStatus;
    private Date projectOnlineTime;
    private Date projectOfflineTime;
    private String projectManager;
    private String productManager;
    private String creator;
    private String identifierCode;

    /**
     * Module Attributes
     */
    private Set<ModuleEntityGetObjectDTO> modules;
    private Set<ParameterEntityGetObjectDTO> parameters;
}
