package com.example.emsbackend.api.dto;


import lombok.Builder;

@Builder
public class DagEntityDTO {

    public String dagId;
    public boolean isPaused;
    public boolean isSubDag;
    public boolean isActive;
    public String owner;

}
