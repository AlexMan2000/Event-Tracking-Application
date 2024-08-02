package com.example.emsbackend.dto.images;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController {

    @PostMapping("/save")
    public ResponseEntity<String> saveImage(MultipartFile file) {

        System.out.println(file.getName());
        return ResponseEntity.ok().build();
    }


}
