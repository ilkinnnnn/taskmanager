package io.github.ilkinnnnn.taskmanager.mapper;


import io.github.ilkinnnnn.taskmanager.dto.task.TaskDto;
import io.github.ilkinnnnn.taskmanager.dto.task.TaskWithAttachmentsDto;
import io.github.ilkinnnnn.taskmanager.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AttachmentMapper.class)
public interface TaskMapper {
    TaskWithAttachmentsDto toDtoWithAttachments(Task task);

    TaskDto toDto(Task task);
}
