package com.example.emsbackend.repository.events;

import com.example.emsbackend.entity.events.EventParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EventParameterMappingRepository extends JpaRepository<EventParameterEntity, EventParameterId> {

    @Query(value="SELECT parameter_id FROM event_parameter WHERE event_id = ?1", nativeQuery = true)
    List<String> findAllParameterEntitiesForEventById(String eventID);

    @Query(value="SELECT event_id FROM event_parameter WHERE parameter_id = ?1", nativeQuery = true)
    List<String> findAllEventEntitiesForParaById(String paraID);


    @Transactional
    @Modifying
    @Query("DELETE FROM EventParameterEntity ep WHERE ep.id.eventId = :eventId AND ep.id.parameterId IN :parameterIds")
    void deleteAllByListOfParameters(@Param("eventId") String eventId, @Param("parameterIds") List<String> parameterMappingToDelete);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO event_parameter (event_id, parameter_id) values (?1, ?2)", nativeQuery = true)
    void insertAllByListOfParameters(String eventId, String parameterMappingToInsert);
}
