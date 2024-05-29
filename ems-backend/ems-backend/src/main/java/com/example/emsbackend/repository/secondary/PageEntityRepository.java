package com.example.emsbackend.repository.secondary;

import com.example.emsbackend.entity.secondary.PageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PageEntityRepository extends JpaRepository<PageEntity, Long> {

}
