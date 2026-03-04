package io.github.ilkinnnnn.taskmanager.service.mapper;


import io.github.ilkinnnnn.taskmanager.dto.task.TaskDto;
import io.github.ilkinnnnn.taskmanager.dto.task.TaskWithAttachmentsDto;
import io.github.ilkinnnnn.taskmanager.entity.Task;

public class TaskMapper {
    private TaskMapper() {}
    public static TaskWithAttachmentsDto toDtoWithAttachments(Task task) {
        return new TaskWithAttachmentsDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate(),
                task.getAttachments().stream().map(AttachmentMapper::toDtoWithoutTaskId).toList(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    public static TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

}
