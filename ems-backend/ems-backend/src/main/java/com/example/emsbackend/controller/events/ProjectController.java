package com.example.emsbackend.controller.events;


import com.example.emsbackend.commons.enums.StatusCode;
import com.example.emsbackend.commons.status.Message;
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

    @GetMapping("/all")
    public List<ProjectEntityGetObjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }


    @PostMapping("/create")
    public ResponseEntity<?> updateProjectById(ProjectEntityUpdateObjectDTO projectEntityUpdateObjectDTO) {
        Message message = projectService.createProject(projectEntityUpdateObjectDTO);
        if (message.getStatusCode() == StatusCode.OK) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProjectById(@PathVariable Long id, ProjectEntityUpdateObjectDTO projectEntityUpdateObjectDTO) {
        Message message = projectService.updateProjectById(id, projectEntityUpdateObjectDTO);
        if (message.getStatusCode() == StatusCode.OK) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }
}
