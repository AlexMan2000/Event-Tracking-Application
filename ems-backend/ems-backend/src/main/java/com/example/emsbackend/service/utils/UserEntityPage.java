package com.example.emsbackend.service.utils;

import org.springframework.data.domain.Sort;

public class UserEntityPage {
    private int pageNumber = 0;
    private int pageSize = 10;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
}
