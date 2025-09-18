package com.example.demo.repository;

import com.example.demo.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class TodoRepository {

    private final Map<UUID, Todo> todoMap = new HashMap<>();
    
    public void save(Todo todo) {
        todoMap.put(todo.getId(), todo);
    }

    public Todo findById(UUID id) {
        return todoMap.get(id);
    }

    public void deleteById(UUID id) {
        todoMap.remove(id);
    }

    public List<Todo> getAll() {
        return todoMap.values().stream().toList();
    }
}
