package com.example.emsbackend.entity.events.entityEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
@Data
@Table(name = "parameter")
public class ParameterEntity {
    @org.springframework.data.annotation.Id
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "gmt_create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gmtCreate;

    @Column(name = "gmt_modify")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gmtModify;

    @Column(name = "parameter_name")
    private String parameterName;

    @Column(name = "parameter_desc")
    private String parameterDesc;

    @Column(name = "parameter_value")
    private String parameterValue;

    @Column(name = "parameter_type")
    private String parameterType;

    @Column(name = "creator")
    private String creator;

    @Column(name = "identifier_code")
    private String identifierCode;



    // Getters and Setters
}

