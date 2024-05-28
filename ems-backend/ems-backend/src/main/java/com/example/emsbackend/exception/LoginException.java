package com.example.emsbackend.exception;


import lombok.Data;

@Data
public class LoginException extends RuntimeException {

    private String code;


    public LoginException(String msg) {
        super(msg);
    }

    public LoginException(String msg, String code) {
        super(msg);
        this.code = code;
    }





}
