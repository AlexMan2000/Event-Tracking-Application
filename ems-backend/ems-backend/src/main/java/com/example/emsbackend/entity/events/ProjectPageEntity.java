package com.example.emsbackend.entity.events;

import com.example.emsbackend.repository.events.ProjectPageId;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="project_page" )
@Data
public class ProjectPageEntity {

    @EmbeddedId
    private ProjectPageId projectPageId;

}
