package com.example.emsbackend.controller.events;


import com.example.emsbackend.commons.enums.https.StatusCode;
import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.ModuleEntitySearchCriteria;
import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.dto.events.getDTO.ModuleEntityGetObjectDTO;
import com.example.emsbackend.dto.events.modifyDTO.ModuleEntityUpdateObjectDTO;
import com.example.emsbackend.service.events.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;


    @GetMapping("/meta")
    public List<GetIdentifiersDTO> getAllMetadata() {
        return moduleService.getAllMetaData();
    }


    @GetMapping("/all")
    public List<ModuleEntityGetObjectDTO> getAllModules() {
        return moduleService.getAllModules();
    }


    @GetMapping("/allfiltered")
    public List<ModuleEntityGetObjectDTO> getAllProjectsFiltered(ModuleEntitySearchCriteria searchCriteria) {
        return moduleService.getAllModulesFiltered(searchCriteria);
    }


    @GetMapping("/{id}")
    public ModuleEntityGetObjectDTO getModuleById(@PathVariable Long id) {
        return moduleService.getModuleById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody ModuleEntityUpdateObjectDTO moduleEntityUpdateObjectDTO) {
        Message message = moduleService.createModule(moduleEntityUpdateObjectDTO);
        if (message.getStatusCode() == StatusCode.OK) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEvent(@RequestBody ModuleEntityUpdateObjectDTO moduleEntityUpdateObjectDTO) {
        Message message = moduleService.updateModule(moduleEntityUpdateObjectDTO);
        if (message.getStatusCode() == StatusCode.OK) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }

}
