package io.github.ilkinnnnn.taskmanager.dto.attachment;

import io.github.ilkinnnnn.taskmanager.entity.AttachmentFileType;
import jakarta.validation.constraints.Size;

public record UpdateAttachmentDto(
        @Size(max = 255) String fileName,
        @Size(max = 500) String fileUrl,
        AttachmentFileType fileType
) {
}
