package com.example.emsbackend.entity.events.mappingEntity;


import com.example.emsbackend.repository.events.compositeId.ProjectParameterId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="ProjectParameterEntity")
@Table(name="project_parameter")
public class ProjectParameterEntity {
    @EmbeddedId
    private ProjectParameterId projectPageId;
}
