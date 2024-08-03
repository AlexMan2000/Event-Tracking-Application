package com.example.emsbackend.controller.images;


import com.example.emsbackend.service.photos.ImageService;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/save")
    public ResponseEntity<String> saveImage(@RequestParam("file") MultipartFile file) {

        try {
            imageService.saveImage(file);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }


        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}/{preview}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id, @PathVariable String preview) {


        GridFSFile file = imageService.getImage(id);
        if (file != null) {
            try {
                InputStream inputStream = imageService.getImageStream(file);
                byte[] imageBytes = inputStream.readAllBytes();

                var mode = preview.equals("preview") ? "inline": "attachment";

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, String.format("%s; filename=\"" + file.getFilename() + "\"", mode))
                        .header(HttpHeaders.CONTENT_TYPE, String.valueOf(MediaType.IMAGE_PNG))
                        .body(imageBytes);
            } catch (IOException e) {
                return ResponseEntity.status(500).body(null);
            }
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }


}
