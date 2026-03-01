package com.tsimo.taskservice.task;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class TaskController {

    private final TaskService taskService;

    TaskController (TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskResponse> createTask(@RequestBody @Valid TaskCreateRequest request) {
        TaskResponse createdTask = taskService.createTask(request);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponse>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }
}
