package com.example.emsbackend.entity.events.mappingEntity;


import com.example.emsbackend.repository.events.compositeId.ModulePageId;
import com.example.emsbackend.repository.events.compositeId.PageParameterId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="PageParameterEntity")
@Table(name="page_parameter")
public class PageParameterEntity {

    @EmbeddedId
    private PageParameterId projectPageId;
}
