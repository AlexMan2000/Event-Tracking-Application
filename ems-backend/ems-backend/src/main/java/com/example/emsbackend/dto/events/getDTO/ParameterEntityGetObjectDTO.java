package com.example.emsbackend.dto.events.getDTO;

import lombok.Data;

import java.util.Date;

@Data
public class ParameterEntityGetObjectDTO {
    private Long id;
    private Date gmtCreate;
    private Date gmtModify;
    private String parameterName;
    private String parameterDesc;
    private String parameterValue;
    private String parameterType;
    private String creator;
    private String identifierCode;

}