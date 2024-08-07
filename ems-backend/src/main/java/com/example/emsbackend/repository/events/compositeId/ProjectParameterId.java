package com.example.emsbackend.repository.events.compositeId;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectParameterId implements Serializable {
    @Column(name="project_id")
    private Long projectId;

    @Column(name="parameter_id")
    private Long parameterId;
}
