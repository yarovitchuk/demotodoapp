package com.example.demo.api;

public class CreateTodoRequest {
    private String description;

    public CreateTodoRequest() {}

    public CreateTodoRequest(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
