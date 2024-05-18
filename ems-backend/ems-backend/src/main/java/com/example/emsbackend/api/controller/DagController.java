package com.example.emsbackend.api.controller;


import com.example.emsbackend.api.dto.DagEntityDTO;
import com.example.emsbackend.service.DagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dags")
public class DagController {
    private final DagService dagService;

    @Autowired
    public DagController(DagService dagService) {
        this.dagService = dagService;
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<DagEntityDTO> getDagById(@PathVariable Long id) {
        DagEntityDTO dagEntityDTO = dagService.getDagDtoById(id);
        return ResponseEntity.ok(dagEntityDTO);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<DagEntityDTO>> getDagByOwners(@PathVariable String name) {
        List<DagEntityDTO> dagEntityDTOs = dagService.getDagDtoByOwners(name);
        return ResponseEntity.ok(dagEntityDTOs);
    }
}
