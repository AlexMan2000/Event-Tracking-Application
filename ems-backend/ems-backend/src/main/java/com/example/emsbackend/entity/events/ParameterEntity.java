package com.example.emsbackend.entity.events;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "parameter")
public class ParameterEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
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
    @org.springframework.data.annotation.Id
    private String identifierCode;



    // Getters and Setters
}

