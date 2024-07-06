package com.example.emsbackend.repository.events;

import com.example.emsbackend.entity.events.PageEntity;
import com.example.emsbackend.entity.events.ProjectEntity;
import com.example.emsbackend.entity.events.ProjectPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectPageMappingRepository extends JpaRepository<ProjectPageEntity, ProjectPageId> {


    @Query(value = "SELECT project_id from project_page where page_id = ?1", nativeQuery = true)
    List<ProjectEntity> getAllProjectsWithPageByID(String pageID);


    @Query(value = "SELECT page_id from project_page where project_id = ?1", nativeQuery = true)
    List<PageEntity> getAllPagesOfProjectByID(String projectID);

}
