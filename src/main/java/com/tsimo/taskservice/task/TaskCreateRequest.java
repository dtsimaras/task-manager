package com.tsimo.taskservice.task;

import jakarta.validation.constraints.NotBlank;

public record TaskCreateRequest(@NotBlank String title) {
}
