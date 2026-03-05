package io.github.ilkinnnnn.taskmanager.dto.attachment;

import io.github.ilkinnnnn.taskmanager.model.enums.AttachmentFileType;

public record AttachmentWithoutTaskIdDto(
        Long id,
        String fileName,
        String fileUrl,
        AttachmentFileType fileType
) {
}
