package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class TodoNotFoundException extends BusinessException {
    public TodoNotFoundException(UUID id) {
        super(HttpStatus.NOT_FOUND, "Todo not found with id = " + id);
    }
}
