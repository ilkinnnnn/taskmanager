package io.github.ilkinnnnn.taskmanager.dto.task;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreateTaskDto(
        @NotBlank @Min(3) @Max(120) String title,
        @Max(500) String description,
        LocalDate dueDate
){}
