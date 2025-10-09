package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class CreateRequestIsInvalid extends BusinessException{
    public CreateRequestIsInvalid() {
        super(HttpStatus.BAD_REQUEST, "Description is null or empty");
    }
}
