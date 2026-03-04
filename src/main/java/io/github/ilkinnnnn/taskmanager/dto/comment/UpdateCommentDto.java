package io.github.ilkinnnnn.taskmanager.dto.comment;

import jakarta.validation.constraints.Size;


public record UpdateCommentDto (
    @Size(max = 500) String comment,
    @Size(max = 255) String author
){}
