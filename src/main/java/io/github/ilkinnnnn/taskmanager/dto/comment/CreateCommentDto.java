package io.github.ilkinnnnn.taskmanager.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCommentDto (
        @NotBlank @Size(max = 500) String comment,
        @NotBlank @Size(max = 255) String author,
        @NotNull Long taskId
){}
