package com.example.emsbackend.controller.events;


import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.dto.events.getDTO.ModuleEntityGetObjectDTO;
import com.example.emsbackend.dto.events.getDTO.ProjectEntityGetObjectDTO;
import com.example.emsbackend.service.events.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/{id}")
    public ModuleEntityGetObjectDTO getModuleById(@PathVariable Long id) {
        return moduleService.getModuleById(id);
    }

}
