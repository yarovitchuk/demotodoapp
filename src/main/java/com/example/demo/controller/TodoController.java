package com.example.demo.controller;

import com.example.demo.api.CreateTodoRequest;
import com.example.demo.api.UpdateTodoRequest;
import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public Todo create(@RequestBody CreateTodoRequest request) {
        return todoService.create(request);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable UUID id, @RequestBody UpdateTodoRequest request) {
        return todoService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        todoService.delete(id);
    }

    @PatchMapping("/{id}")
    public Todo complete(@PathVariable UUID id) {
        return todoService.complete(id);
    }

    @GetMapping("/all")
    public List<Todo> getAll() {
        return todoService.getAll();
    }
}
