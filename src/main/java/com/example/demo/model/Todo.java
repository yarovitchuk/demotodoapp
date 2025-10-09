package com.example.demo.model;

import java.util.UUID;

public class Todo {
    private UUID id;
    private String description;
    private TodoStatus status;

    public Todo(UUID id, String description, TodoStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }
}
