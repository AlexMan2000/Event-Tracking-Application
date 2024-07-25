package com.example.emsbackend.criteria_utils.searching;

import com.example.emsbackend.criteria_utils.searching.SearchCriteria;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectEntitySearchCriteria implements SearchCriteria {

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
