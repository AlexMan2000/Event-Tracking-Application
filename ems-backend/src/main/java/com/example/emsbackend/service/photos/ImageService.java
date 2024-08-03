package com.example.emsbackend.service.photos;

import com.example.emsbackend.dto.auths.ProfileImageMetadata;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


public interface ImageService {
    String saveImage(MultipartFile file) throws IOException;

    String saveImage(byte[] imageBytes, ProfileImageMetadata profileImageMetadata) throws IOException;

    GridFSFile getImage(String id);

    InputStream getImageStream(GridFSFile file);
}
