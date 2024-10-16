package com.example.demo.rest;

public class UserErrorReponse {
    private int code;
    private String message;
    public UserErrorReponse() {}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserErrorReponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
