package com.example.emsbackend.service.photos.impl;

import com.example.emsbackend.service.photos.ImageService;



import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private GridFsTemplate gridFsTemplate;


    @Autowired
    private MongoDatabaseFactory mongoDatabaseFactory;

    @Override
    public String saveImage(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ObjectId objectId = gridFsTemplate.store(inputStream, file.getOriginalFilename(), file.getContentType());
        return objectId.toString();
    }

    @Override
    public GridFSFile getImage(String id) {
        return gridFsTemplate.findOne(new Query()
                .addCriteria(Criteria.where("_id").is(id)));
    }

    @Override
    public InputStream getImageStream(GridFSFile file) {
        return GridFSBuckets.create(mongoDatabaseFactory.getMongoDatabase()).openDownloadStream(file.getObjectId());
    }
}

