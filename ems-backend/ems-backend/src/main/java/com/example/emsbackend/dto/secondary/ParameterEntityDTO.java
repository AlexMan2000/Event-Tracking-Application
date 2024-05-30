package com.example.emsbackend.dto.secondary;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class ParameterEntityDTO {
    private Long id;
    private Date gmtCreate;
    private Date gmtModify;
    private String parameterName;
    private String parameterDesc;
    private String parameterValue;
    private String parameterType;
    private Map<String, List<String>> parameterScope; // 生效范围，用于展示
    private String creator;
    private String identifierCode;

}
