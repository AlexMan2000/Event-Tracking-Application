package com.example.emsbackend.repository.events.entityRepository;

import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.entity.events.entityEntity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventEntityRepository extends JpaRepository<EventEntity, Long> {



        @Query(value = "SELECT id, identifier_code as identifierCode from event", nativeQuery = true)
        List<GetIdentifiersDTO> findAllMetaData();


}
