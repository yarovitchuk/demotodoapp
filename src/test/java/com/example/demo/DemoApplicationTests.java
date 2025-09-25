package com.example.demo;

import com.example.demo.api.CreateTodoRequest;
import com.example.demo.service.TodoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private TodoService todoService;

    @Test
    void contextLoads() {
        var request = new CreateTodoRequest("Все таки сходить на треню");
        var todo = todoService.create(request);
        var savedTodo = todoService.getByDescription(todo.getDescription()).getFirst();

        Assertions.assertEquals(todo.getId(), savedTodo.getId());
    }
}
