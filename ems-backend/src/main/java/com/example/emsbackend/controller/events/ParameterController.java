package com.example.emsbackend.controller.events;

import com.example.emsbackend.commons.enums.https.StatusCode;
import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.ParameterEntitySearchCriteria;
import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.dto.events.getDTO.ParameterEntityGetObjectDTO;
import com.example.emsbackend.dto.events.modifyDTO.ParameterEntityUpdateObjectDTO;
import com.example.emsbackend.service.events.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parameters")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;


    @GetMapping("/meta")
    public List<GetIdentifiersDTO> getAllMetadata() {
        return parameterService.getAllMetaData();
    }



    @GetMapping("/all")
    public List<ParameterEntityGetObjectDTO> getAllProjects() {
        return parameterService.getAllParameters();
    }


    @GetMapping("/allfiltered")
    public List<ParameterEntityGetObjectDTO> getAllProjectsFiltered(ParameterEntitySearchCriteria searchCriteria) {
        return parameterService.getAllParametersFiltered(searchCriteria);
    }

    @GetMapping("/{id}")
    public ParameterEntityGetObjectDTO getProjectById(@PathVariable Long id) {
        return parameterService.getParameterById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createParameter(@RequestBody ParameterEntityUpdateObjectDTO parameterEntityUpdateObjectDTO) {
        Message message = parameterService.createParameter(parameterEntityUpdateObjectDTO);
        if (message.getStatusCode() == StatusCode.OK) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateParameter(@RequestBody ParameterEntityUpdateObjectDTO parameterEntityUpdateObjectDTO) {
        Message message = parameterService.updateParameter(parameterEntityUpdateObjectDTO);
        if (message.getStatusCode() == StatusCode.OK) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }

}
