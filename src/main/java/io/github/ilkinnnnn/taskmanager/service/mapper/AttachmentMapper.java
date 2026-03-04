package io.github.ilkinnnnn.taskmanager.service.mapper;


import io.github.ilkinnnnn.taskmanager.dto.attachment.AttachmentDto;
import io.github.ilkinnnnn.taskmanager.dto.attachment.AttachmentWithoutTaskIdDto;
import io.github.ilkinnnnn.taskmanager.entity.Attachment;

public class AttachmentMapper {
    private AttachmentMapper() {}

    public static AttachmentWithoutTaskIdDto toDtoWithoutTaskId(Attachment attachment) {
        return new AttachmentWithoutTaskIdDto(
                attachment.getId(),
                attachment.getFileName(),
                attachment.getFileUrl(),
                attachment.getFileType()
        );
    }

    public static AttachmentDto toDto(Attachment attachment) {
        return new AttachmentDto(
                attachment.getId(),
                attachment.getFileName(),
                attachment.getFileUrl(),
                attachment.getFileType(),
                attachment.getTask().getId()
        );
    }
}