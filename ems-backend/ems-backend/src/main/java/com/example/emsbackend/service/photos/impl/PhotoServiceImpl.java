package com.example.emsbackend.service.photos.impl;

import com.example.emsbackend.service.photos.PhotoService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PhotoServiceImpl implements PhotoService {

    public static void main(String[] args) {
        Map<Integer, Boolean> visited = new HashMap<>();

        visited.put(0, true);
        visited.put(1, false);
        visited.put(2, false);

        for (Boolean key: visited.values()) {
            System.out.println(key);
        }

        int size = visited.values().stream().filter(elem -> !elem).collect(Collectors.toList()).size();
        System.out.println(size);

    }

}
