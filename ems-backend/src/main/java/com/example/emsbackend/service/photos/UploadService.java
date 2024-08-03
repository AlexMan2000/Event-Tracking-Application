package com.example.emsbackend.service.photos;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {

    String saveImageTemporarily(MultipartFile file) throws IOException;

    byte[] getImageFromTempStorage(String imageId);

    void deleteImageFromTempStorage(String imageId);
}
