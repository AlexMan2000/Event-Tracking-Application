package com.example.emsbackend.api.controller;


import com.example.emsbackend.api.dto.DagEntityDTO;
import com.example.emsbackend.service.impl.DagService;
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


    @GetMapping("/id/{dagId}")
    public ResponseEntity<DagEntityDTO> getDagById(@PathVariable String dagId) {
        DagEntityDTO dagEntityDTO = dagService.getDagDtoById(dagId);
        return dagEntityDTO != null ? ResponseEntity.ok(dagEntityDTO): ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<DagEntityDTO>> getDagByOwners(@PathVariable String name) {
        List<DagEntityDTO> dagEntityDTOs = dagService.getDagDtoByOwners(name);
        return dagEntityDTOs.size() != 0 ? ResponseEntity.ok(dagEntityDTOs): ResponseEntity.notFound().build();
    }





}
