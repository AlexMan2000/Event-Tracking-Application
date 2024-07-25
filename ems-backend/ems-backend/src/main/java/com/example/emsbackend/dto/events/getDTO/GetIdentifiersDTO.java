package com.example.emsbackend.dto.events.getDTO;


import lombok.Data;

@Data
public class GetIdentifiersDTO {
    private Long id; // 主键
    private String identifierCode;  // 编码
}
