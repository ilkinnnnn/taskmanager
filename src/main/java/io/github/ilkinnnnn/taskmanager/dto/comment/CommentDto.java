package io.github.ilkinnnnn.taskmanager.dto.comment;

import java.time.LocalDateTime;

public record CommentDto(
        Long id,
        String comment,
        String author,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long taskId
) {
}
