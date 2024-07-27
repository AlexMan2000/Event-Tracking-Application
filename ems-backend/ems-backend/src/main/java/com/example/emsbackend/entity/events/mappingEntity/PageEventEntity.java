package com.example.emsbackend.entity.events.mappingEntity;


import com.example.emsbackend.repository.events.compositeId.ModulePageId;
import com.example.emsbackend.repository.events.compositeId.PageEventId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="PageEventEntity")
@Table(name="page_event")
public class PageEventEntity {

    @EmbeddedId
    private PageEventId id;
}
