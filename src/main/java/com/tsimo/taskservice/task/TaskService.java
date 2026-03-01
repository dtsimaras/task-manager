package com.tsimo.taskservice.task;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class TaskService {

    private final TaskRepository taskRepository;

    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    TaskResponse createTask(TaskCreateRequest request) {
        Task task = taskRepository.save(new Task(request.title()));
        return mapTask(task);
    }

    List<TaskResponse> findAll() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskResponse> response = new ArrayList<>();
        for (Task task : tasks) {
            response.add(mapTask(task));
        }
        return response;
    }

    private TaskResponse mapTask(Task t) {
        return new TaskResponse(t.getId(), t.getTitle());
    }
}
