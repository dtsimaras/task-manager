package com.tsimo.taskservice.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TaskService {

    private final TaskRepository taskRepository;

    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    TaskResponse createTask(TaskCreateRequest request) {
        Task task = taskRepository.save(new Task(request.title(), request.description()));
        return mapTask(task);
    }

    Page<TaskResponse> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable).map(this::mapTask);
    }

    @Deprecated
    List<TaskResponse> findAll() {
        return taskRepository.findAll().stream().map(this::mapTask).toList();
    }

    TaskResponse findById(Long id) throws TaskNotFoundException {
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        return mapTask(task);
    }

    TaskResponse updateTask(Long id, TaskUpdateRequest request) throws TaskNotFoundException {
        Task taskDB = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);

        taskDB.setTitle(request.title());
        taskDB.setDescription(request.description());
        taskDB.setStatus(request.status());

        taskDB = taskRepository.save(taskDB);

        return mapTask(taskDB);
    }

    void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskResponse mapTask(Task t) {
        return new TaskResponse(t.getId(), t.getTitle(), t.getDescription(), t.getStatus(), t.getCreatedAt(), t.getUpdatedAt());
    }
}
