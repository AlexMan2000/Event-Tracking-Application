package com.example.emsbackend.repository.events.mappingRepository;

import com.example.emsbackend.entity.events.mappingEntity.EventParameterEntity;
import com.example.emsbackend.repository.events.compositeId.EventParameterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface EventParameterMappingRepository extends JpaRepository<EventParameterEntity, EventParameterId> {

    @Query(value="SELECT parameter_id FROM event_parameter WHERE event_id = ?1", nativeQuery = true)
    List<Long> findAllParameterEntitiesForEventById(Long eventID);

    @Query(value="SELECT event_id FROM event_parameter WHERE parameter_id = ?1", nativeQuery = true)
    List<Long> findAllEventEntitiesForParaById(Long paraID);


//    @Transactional
//    @Modifying
//    @Query("DELETE FROM EventParameterEntity ep WHERE ep.id.eventId = :eventId AND ep.id.parameterId IN :parameterIds")
//    void deleteAllByListOfParameters(@Param("eventId") Long eventId, @Param("parameterIds") List<Long> parameterMappingToDelete);


//    @Transactional
//    @Modifying
//    @Query(value = "INSERT INTO event_parameter (event_id, parameter_id) values (?1, ?2)", nativeQuery = true)
//    void insertAllByListOfParameters(Long eventId, Long parameterMappingToInsert);
}
