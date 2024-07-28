package com.example.emsbackend.entity.photos;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document
@Data
public class PhotoEntity {


    @Id
    private String id;

    @Field("file_name")
    private String fileName;

    @Field("date_time")
    private Date dateTime;

    @Field("user_id")
    private String userId;

    private List<String> comments;

    @Field("__v")
    private int version;
}
