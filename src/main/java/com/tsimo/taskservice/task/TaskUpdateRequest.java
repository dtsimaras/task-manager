package com.tsimo.taskservice.task;

import jakarta.validation.constraints.NotBlank;

public record TaskUpdateRequest(@NotBlank String title, String description, TaskStatus status) {
}
