package io.github.ilkinnnnn.taskmanager.mapper;

import io.github.ilkinnnnn.taskmanager.dto.attachment.AttachmentDto;
import io.github.ilkinnnnn.taskmanager.dto.attachment.AttachmentWithoutTaskIdDto;
import io.github.ilkinnnnn.taskmanager.model.Attachment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {
    AttachmentWithoutTaskIdDto toDtoWithoutTaskId(Attachment attachment);

    @Mapping(source = "task.id", target = "taskId")
    AttachmentDto toDto(Attachment attachment);
}
