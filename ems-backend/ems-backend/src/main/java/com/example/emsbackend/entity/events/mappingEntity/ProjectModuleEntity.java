package com.example.emsbackend.entity.events.mappingEntity;

import com.example.emsbackend.repository.events.compositeId.ProjectModuleId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="ProjectModuleEntity")
@Table(name="project_module")
public class ProjectModuleEntity {

    @EmbeddedId
    private ProjectModuleId projectPageId;

}
