package com.example.emsbackend.repository.secondary;

import jakarta.persistence.Column;

import java.io.Serializable;

public class ProjectPageId implements Serializable {
    @Column(name="project_id")
    private String projectId;
    @Column(name="page_id")
    private String pageId;
}
