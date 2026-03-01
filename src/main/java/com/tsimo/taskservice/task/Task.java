package com.tsimo.taskservice.task;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    Task() {}

    Task(String title) {
        this.title = title;
        this.status = TaskStatus.OPEN;
        this.createdAt = LocalDateTime.now();
    }
    
}
