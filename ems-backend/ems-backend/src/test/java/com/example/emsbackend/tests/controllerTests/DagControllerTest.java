//package com.example.emsbackend.tests.controllerTests;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.mockito.BDDMockito.given;
//
//
//import com.example.emsbackend.api.controller.DagController;
//import com.example.emsbackend.api.dto.DagEntityDTO;
//import com.example.emsbackend.service.DagService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.http.MediaType;
//
//import java.util.Arrays;
//import java.util.List;
//
//
//@WebMvcTest(DagController.class)
//public class DagControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private DagService dagService;
//
//    @Test
//    public void getDagByIdTest() throws Exception {
//        DagEntityDTO mockDag = new DagEntityDTO();
//        mockDag.setId(1L);
//        mockDag.setOwners("Test DAG");
//        mockDag.setIsActive(true);
//
//        given(dagService.getDagDtoById("dag_1")).willReturn(mockDag);
//
//        mockMvc.perform(get("/dags/id/1")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Test DAG"))
//                .andExpect(jsonPath("$.isActive").value(true));
//    }
//
//    @Test
//    public void getDagByOwnersTest() throws Exception {
//        List<DagEntityDTO> dagEntityDTOs = Arrays.asList(
//                new DagEntityDTO(1L, "Test DAG 1", true),
//                new DagEntityDTO(2L, "Test DAG 2", false)
//        );
//
//        given(dagService.getDagDtoByOwners("owner1")).willReturn(dagEntityDTOs);
//
//        mockMvc.perform(get("/dags/name/owner1")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].name").value("Test DAG 1"))
//                .andExpect(jsonPath("$[0].isActive").value(true))
//                .andExpect(jsonPath("$[1].id").value(2))
//                .andExpect(jsonPath("$[1].name").value("Test DAG 2"))
//                .andExpect(jsonPath("$[1].isActive").value(false));
//    }
//}