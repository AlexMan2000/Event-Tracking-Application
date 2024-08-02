package com.example.emsbackend.controller.events;


import com.example.emsbackend.commons.enums.https.StatusCode;
import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.ProjectEntitySearchCriteria;
import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.dto.events.getDTO.ProjectEntityGetObjectDTO;
import com.example.emsbackend.dto.events.modifyDTO.ProjectEntityUpdateObjectDTO;
import com.example.emsbackend.service.events.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {


    @Autowired
    private ProjectService projectService;


    @GetMapping("/meta")
    public List<GetIdentifiersDTO> getAllMetadata() {
        return projectService.getAllMetaData();
    }



    @GetMapping("/all")
    public List<ProjectEntityGetObjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }


    @GetMapping("/allfiltered")
    public List<ProjectEntityGetObjectDTO> getAllProjectsFiltered(ProjectEntitySearchCriteria searchCriteria) {
        return projectService.getAllProjectsFiltered(searchCriteria);
    }

    @GetMapping("/{id}")
    public ProjectEntityGetObjectDTO getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody ProjectEntityUpdateObjectDTO projectEntityUpdateObjectDTO) {
        Message message = projectService.createProject(projectEntityUpdateObjectDTO);
        if (message.getStatusCode() == StatusCode.OK) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProject(@RequestBody ProjectEntityUpdateObjectDTO projectEntityUpdateObjectDTO) {
        Message message = projectService.updateProject(projectEntityUpdateObjectDTO);
        if (message.getStatusCode() == StatusCode.OK) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }
}
