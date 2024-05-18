package com.example.emsbackend.tests.lombokTest;

import lombok.Cleanup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

public class TestLombokCleanup {
    public static void main(String[] args) throws IOException {
        autoCleanUp(args);
        manualCleanup(args);
    }

    public static void autoCleanUp(String[] args) throws IOException {
        @Cleanup InputStream fis = new FileInputStream("F:\\Study_Notes_Backup\\Full_Stack_Projects\\SpringBoot3_JPA_React\\ems-backend\\ems-backend\\src\\main\\java\\com\\example\\emsbackend\\tests\\lombokTest\\test.c");
        int nread;
        byte[] buffer = new byte[1024];
        while ((nread = fis.read(buffer)) >= 0) {
            System.out.println(nread);
        }
    }

    public static void manualCleanup(String[] args) throws IOException {
        @Cleanup InputStream fis = new FileInputStream("F:\\Study_Notes_Backup\\Full_Stack_Projects\\SpringBoot3_JPA_React\\ems-backend\\ems-backend\\src\\main\\java\\com\\example\\emsbackend\\tests\\lombokTest\\test.c");
        try{
            int nread;
            byte[] buffer = new byte[1024];
            while ((nread = fis.read(buffer)) >= 0) {
                System.out.println(nread);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (Collections.singletonList(fis).get(0) != null) {
                fis.close();
            }
        }
    }

}
