package com.example.emsbackend.dto.auths;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileImageMetadata {

    private String imageName;
    private String contentType;
    private String imageId;
}
