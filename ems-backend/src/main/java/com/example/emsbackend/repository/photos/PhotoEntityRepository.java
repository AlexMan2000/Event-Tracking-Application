package com.example.emsbackend.repository.photos;

import com.example.emsbackend.entity.photos.PhotoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoEntityRepository extends MongoRepository<PhotoEntity, String> {



}
