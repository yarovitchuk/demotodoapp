package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class TodoAlreadyCompletedException extends BusinessException{
    public TodoAlreadyCompletedException(UUID id) {
        super(HttpStatus.BAD_REQUEST, "Todo already completed with id = " + id);
    }
}
