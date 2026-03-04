package io.github.ilkinnnnn.taskmanager.service.mapper;

import io.github.ilkinnnnn.taskmanager.dto.comment.CommentDto;
import io.github.ilkinnnnn.taskmanager.entity.Comment;

public class CommentMapper {
    private CommentMapper(){}

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getComment(),
                comment.getAuthor(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.getTask().getId()
        );
    }
}
