package com.example.emsbackend.entity.events.mappingEntity;


import com.example.emsbackend.repository.events.compositeId.ModuleParameterId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="ModuleParameterEntity")
@Table(name="module_parameter")
public class ModuleParameterEntity {

    @EmbeddedId
    private ModuleParameterId moduleParameterId;
}
