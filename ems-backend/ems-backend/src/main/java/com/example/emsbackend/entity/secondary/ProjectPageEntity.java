package com.example.emsbackend.entity.secondary;

import com.example.emsbackend.repository.secondary.ProjectPageId;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="project_page" )
@Data
public class ProjectPageEntity {

    @EmbeddedId
    private ProjectPageId projectPageId;

}
