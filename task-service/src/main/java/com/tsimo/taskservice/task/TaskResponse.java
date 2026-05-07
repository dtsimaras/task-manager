package com.tsimo.taskservice.task;

import java.time.LocalDateTime;

public record TaskResponse(Long id, String title, String description, TaskStatus status, LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
}
