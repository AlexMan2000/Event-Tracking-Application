package com.example.emsbackend.controller.images;

import com.example.emsbackend.service.photos.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/uploads")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload-temp-image")
    public ResponseEntity<String> uploadTempImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageId = uploadService.saveImageTemporarily(file);
            return ResponseEntity.ok(imageId);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading image: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getTemporaryImage(@PathVariable String id) {
        byte[] imageBytes = uploadService.getImageFromTempStorage(id);
        if (imageBytes != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=temp.png")
                    .header(HttpHeaders.CONTENT_TYPE, String.valueOf(MediaType.IMAGE_PNG))
                    .body(imageBytes);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }


}
