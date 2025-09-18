package com.example.demo.service;

import com.example.demo.api.CreateTodoRequest;
import com.example.demo.api.UpdateTodoRequest;
import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository todoRepository) {
        this.repository = todoRepository;
    }

    public Todo create(CreateTodoRequest todoRequest) {
        if (todoRequest.getDescription() == null || todoRequest.getDescription().isEmpty()) {
            return null;
        }

        var todo = new Todo(
                UUID.randomUUID(),
                todoRequest.getDescription(),
                false
        );

        repository.save(todo);
        return todo;
    }

    public Todo update(UUID id, UpdateTodoRequest todoRequest) {
        if (todoRequest.getDescription() == null || todoRequest.getDescription().isEmpty()) {
            return null;
        }

        Todo todo = repository.findById(id);
        if (todo == null) {
            // throw TodoNotFoundException(id);
            return null;
        }

        todo.setDescription(todoRequest.getDescription());

        repository.save(todo);
        return todo;
    }

    public Todo complete(UUID id) {
        Todo todo = repository.findById(id);
        if (todo == null) {
            // throw TodoNotFoundException(id);
            return null;
        }

        if (todo.isCompleted()) {
            // throw TodoAlreadyCompletedException(id);
            return null;
        }

        todo.setIsCompleted(true);

        repository.save(todo);
        return todo;
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public List<Todo> getAll() {
        return repository.getAll();
    }
}
