package io.github.ilkinnnnn.taskmanager.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateTaskDto(
        @NotBlank @Size(min = 3, max = 120) String title,
        @Size(max = 500) String description,
        LocalDate dueDate
){}
