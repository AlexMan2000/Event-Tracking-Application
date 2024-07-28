package com.example.emsbackend.repository.events.compositeId;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;


@Data
public class PageParameterId implements Serializable{
    @Column(name="page_id")
    private Long pageId;
    @Column(name="parameter_id")
    private Long parameterId;
}
