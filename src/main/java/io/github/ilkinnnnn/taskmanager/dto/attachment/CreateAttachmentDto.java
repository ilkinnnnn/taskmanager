package io.github.ilkinnnnn.taskmanager.dto.attachment;

import io.github.ilkinnnnn.taskmanager.model.enums.AttachmentFileType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAttachmentDto(
        @Size(max = 255) @NotBlank String fileName,
        @Size(max = 500) @NotBlank String fileUrl,
        @NotNull AttachmentFileType fileType,
        @NotNull Long taskId
) {
}
