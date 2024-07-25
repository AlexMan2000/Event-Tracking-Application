package com.example.emsbackend.controller.events;

import com.example.emsbackend.criteria_utils.searching.ParameterEntitySearchCriteria;
import com.example.emsbackend.dto.events.entityDTO.ParameterEntityDTO;
import com.example.emsbackend.service.events.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parameters")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;


    @GetMapping("/allname")
    public List<String> getAllParameterIdentifers() {
        return parameterService.getAllParameterIdentifiers();
    }


    @GetMapping("/allIds")
    public List<Long> getAllParameterIds() {
        return parameterService.getAllParameterIds();
    }

    @GetMapping("/getdropdown")
    public List<Map<String, String>> getDropdownInformation() {
        return parameterService.getDropdownInformation();

    }

//    @GetMapping("/all")
//    public ResponseEntity<List<ParameterEntityDTO>> getAllParameters() {
//        return ResponseEntity.ok(parameterService.getAllParameters());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ParameterEntityDTO> getParameterById(@PathVariable Long id) {
        return ResponseEntity.ok(parameterService.getParameterById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ParameterEntityDTO>> getAllParametersFiltered(ParameterEntitySearchCriteria searchCriteria) {
        return ResponseEntity.ok(parameterService.getAllParametersFiltered(searchCriteria));
    }

}
