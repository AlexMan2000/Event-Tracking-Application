package com.example.emsbackend.criteria_utils.searching;

import com.example.emsbackend.criteria_utils.searching.SearchCriteria;

import java.util.Date;

public class ParameterEntitySearchCriteria implements SearchCriteria {
    private Long id;
    private Date gmtCreate;
    private Date gmtModify;
    private String parameterName;
    private String parameterDesc;
    private String parameterValue;
    private String parameterType;
    //    private Map<String, List<String>> parameterScope; // 生效范围，用于展示
    private String creator;
    private String identifierCode;

}
