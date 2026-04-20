package com.tsimo.taskservice.task;

import jakarta.persistence.*;
import lombok.AccessLevel;
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

    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Setter(AccessLevel.NONE)
    private LocalDateTime updatedAt;

    Task() {
        this.status = TaskStatus.OPEN;
        this.createdAt = LocalDateTime.now();
    }

    Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.OPEN;
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
