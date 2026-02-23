package io.github.ilkinnnnn.taskmanager.dto.task;

import io.github.ilkinnnnn.taskmanager.entity.TaskStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;

public record UpdateTaskDto(
        @Min(3) @Max(120) String title,
        @Max(500) String description,
        LocalDate dueDate,
        TaskStatus status
) {
}
