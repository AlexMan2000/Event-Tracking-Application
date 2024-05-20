package com.example.emsbackend.api.controller;


import com.example.emsbackend.api.dto.DagEntityDTO;
import com.example.emsbackend.service.impl.DagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/dags")
public class DagController {
    private final DagServiceImpl dagService;

    @Autowired
    public DagController(DagServiceImpl dagService) {
        this.dagService = dagService;
    }

    /**
     * Get the record with dagId specified by user
     * @param dagId
     * @return The record object with dag_id = dagId
     */
    @GetMapping("/id/{dagId}")
    public ResponseEntity<DagEntityDTO> getDagById(@PathVariable String dagId) {
        DagEntityDTO dagEntityDTO = dagService.getDagDtoById(dagId);
        return dagEntityDTO != null ? ResponseEntity.ok(dagEntityDTO): ResponseEntity.notFound().build();
    }


    /**
     * Get the record with owner specified by user
     * @param name owner name
     * @return The record object with owners = name
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<List<DagEntityDTO>> getDagByOwners(@PathVariable String name) {
        List<DagEntityDTO> dagEntityDTOs = dagService.getDagDtoByOwners(name);
        return dagEntityDTOs.size() != 0 ? ResponseEntity.ok(dagEntityDTOs): ResponseEntity.notFound().build();
    }


    /**
     * Get all dag entities
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<List<DagEntityDTO>> getAllDags() {
        return ResponseEntity.ok(dagService.getAllDags());
    }


    /**
     * Check if a dagId is already in the database
     * @param id the id to be checked
     * @return true if there is duplicate key; false otherwise
     */
    @GetMapping("/validate/dagId/{id}")
    public ResponseEntity<Boolean> checkDuplicateId(@PathVariable String id) {
        // Asychronous task
        CompletableFuture<Boolean> res = CompletableFuture.supplyAsync(() -> dagService.checkIDDuplicate(id));
        try {
            // Block at get()
            return ResponseEntity.ok(res.get());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    // RequestBody here to extract the posted object from json file format
    @PostMapping("/post")
    public ResponseEntity<DagEntityDTO> insertOrUpdateDag(@RequestBody DagEntityDTO dagEntityDTO) {
        System.out.println(dagEntityDTO);
        try {
            DagEntityDTO savedDagEntity = dagService.createOrUpdateDagEntity(dagEntityDTO);
            return new ResponseEntity<>(savedDagEntity, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }



    /* PUT APIs */
    @PutMapping("/update/{dagId}")
    public ResponseEntity<DagEntityDTO> updateDagEntity(@PathVariable String dagId, @RequestBody DagEntityDTO dagEntity) {
        DagEntityDTO dagEntityDTO = dagService.updateDagEntity(dagId, dagEntity);
        if (dagEntityDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dagEntityDTO);

    }



    /* DELETE APIs */
    @DeleteMapping("/delete/{dagId}")
    public ResponseEntity<String> deleteDagEntity(@PathVariable String dagId) {
        DagEntityDTO dagEntityDTO = dagService.deleteDagEntity(dagId);
        if (dagEntityDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Dag deleted successfully!");
    }


}
