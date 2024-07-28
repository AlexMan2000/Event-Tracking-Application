package com.example.emsbackend.commons.status;


import com.example.emsbackend.commons.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private StatusCode statusCode;
    private String message;
}
