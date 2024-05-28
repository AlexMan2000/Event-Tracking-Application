package com.example.emsbackend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalException {

    // Will be executed whenever there is LoginException thrown from any controllers
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> serviceException(LoginException e) {

        HttpStatus returnStatus;
        switch(e.getCode()) {
            // 权限错误 401
            case "401":
                returnStatus = HttpStatus.UNAUTHORIZED;
                break;
            // 用户已存在
            case "208":
                returnStatus = HttpStatus.ALREADY_REPORTED;
                break;
            case "500":
                returnStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
            default:
                returnStatus = HttpStatus.BAD_REQUEST;
                break;
        }

        return new ResponseEntity<>(e.getCode() + e.getMessage(), returnStatus);
    }
}
