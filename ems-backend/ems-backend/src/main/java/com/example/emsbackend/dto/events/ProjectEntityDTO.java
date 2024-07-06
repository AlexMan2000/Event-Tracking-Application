package com.example.emsbackend.dto.events;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectEntityDTO {
    private List<ParameterEntityDTO> parameters;
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
}
