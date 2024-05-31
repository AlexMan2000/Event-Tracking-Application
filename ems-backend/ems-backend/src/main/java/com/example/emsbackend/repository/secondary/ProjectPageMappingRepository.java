package com.example.emsbackend.repository.secondary;

import com.example.emsbackend.entity.secondary.PageEntity;
import com.example.emsbackend.entity.secondary.ProjectEntity;
import com.example.emsbackend.entity.secondary.ProjectPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectPageMappingRepository extends JpaRepository<ProjectPageEntity, ProjectPageId> {


    @Query(value = "SELECT project_id from project_page where page_id = ?1", nativeQuery = true)
    List<ProjectEntity> getAllProjectsWithPageByID(String pageID);


    @Query(value = "SELECT page_id from project_page where project_id = ?1", nativeQuery = true)
    List<PageEntity> getAllPagesOfProjectByID(String projectID);

}
