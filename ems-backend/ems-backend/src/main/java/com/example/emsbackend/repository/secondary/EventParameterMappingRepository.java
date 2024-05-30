package com.example.emsbackend.repository.secondary;

import com.example.emsbackend.entity.secondary.EventParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventParameterMappingRepository extends JpaRepository<EventParameterEntity, EventParameterId> {

    @Query(value="SELECT parameter_id FROM event_parameter WHERE event_id = ?1", nativeQuery = true)
    List<String> findAllParameterEntitiesForEventById(String eventID);

}
