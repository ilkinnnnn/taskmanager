package io.github.ilkinnnnn.taskmanager.dto.task;

import io.github.ilkinnnnn.taskmanager.entity.TaskStatus;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateTaskDto(
        @Size(min = 3, max = 120) String title,
        @Size(max = 500) String description,
        LocalDate dueDate,
        TaskStatus status
) {
}
