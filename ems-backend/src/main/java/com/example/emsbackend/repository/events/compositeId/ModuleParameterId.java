package com.example.emsbackend.repository.events.compositeId;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleParameterId implements Serializable {
    @Column(name="module_id")
    private Long moduleId;

    @Column(name="parameter_id")
    private Long parameterId;
}
