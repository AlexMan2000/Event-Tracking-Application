package com.example.emsbackend.dto.events.getDTO;

import com.example.emsbackend.dto.events.entityDTO.ModuleEntityDTO;
import com.example.emsbackend.dto.events.entityDTO.ParameterEntityDTO;
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
    private Set<ModuleEntityDTO> modules;
    private Set<ParameterEntityDTO> params;
}
