package io.github.ilkinnnnn.taskmanager.mapper;

import io.github.ilkinnnnn.taskmanager.dto.comment.CommentDto;
import io.github.ilkinnnnn.taskmanager.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "taskId", source = "task.id")
    CommentDto toDto(Comment comment);
}
