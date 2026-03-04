package io.github.ilkinnnnn.taskmanager.dto.task;

import io.github.ilkinnnnn.taskmanager.dto.attachment.AttachmentWithoutTaskIdDto;
import io.github.ilkinnnnn.taskmanager.entity.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record TaskWithAttachmentsDto(
        Long id,
        String title,
        String description,
        TaskStatus status,
        LocalDate dueDate,
        List<AttachmentWithoutTaskIdDto> attachments,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
