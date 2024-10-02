package com.example.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserErrorReponse> handleUsernameNotFoundException(UserNotFoundException e) {
        UserErrorReponse userErrorReponse = new UserErrorReponse();
        userErrorReponse.setCode(404);
        userErrorReponse.setMessage(e.getMessage());
        return new ResponseEntity<>(userErrorReponse, HttpStatus.NOT_FOUND);
    }
}
