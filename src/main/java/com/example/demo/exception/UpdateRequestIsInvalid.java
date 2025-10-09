package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class UpdateRequestIsInvalid extends BusinessException{
    public UpdateRequestIsInvalid() {
        super(HttpStatus.BAD_REQUEST, "Description is null or empty");
    }
}
