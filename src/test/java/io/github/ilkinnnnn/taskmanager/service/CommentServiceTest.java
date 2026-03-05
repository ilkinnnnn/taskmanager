package io.github.ilkinnnnn.taskmanager.service;


import io.github.ilkinnnnn.taskmanager.dto.comment.CommentDto;
import io.github.ilkinnnnn.taskmanager.dto.comment.CreateCommentDto;
import io.github.ilkinnnnn.taskmanager.exception.NotFoundException;
import io.github.ilkinnnnn.taskmanager.exception.task.TaskNotFoundException;
import io.github.ilkinnnnn.taskmanager.mapper.CommentMapper;
import io.github.ilkinnnnn.taskmanager.model.Comment;
import io.github.ilkinnnnn.taskmanager.model.Task;
import io.github.ilkinnnnn.taskmanager.repository.CommentRepo;
import io.github.ilkinnnnn.taskmanager.repository.TaskRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    CommentRepo commentRepo;

    @Mock
    TaskRepo taskRepo;

    @Mock
    CommentMapper commentMapper;

    @InjectMocks
    CommentService service;

    @Test
    void shouldCreateComment() {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        Long taskId = 1L;

        CreateCommentDto request = new CreateCommentDto(
                "comment", "author", taskId);

        CommentDto response = new CommentDto(
                1L, "comment", "author", createdAt, updatedAt, taskId);

        Task task = new Task();
        Comment comment = new Comment();
        comment.setComment("comment");
        comment.setAuthor("author");

        when(taskRepo.findById(taskId)).thenReturn(Optional.of(task));
        when(commentRepo.save(any(Comment.class))).thenReturn(comment);
        when(commentMapper.toDto(comment)).thenReturn(response);

        CommentDto result = service.create(request);

        assertEquals(request.comment(), result.comment());
        assertEquals(request.author(), result.author());
        assertEquals(request.taskId(), result.taskId());

        verify(taskRepo).findById(taskId);
        verify(commentRepo).save(any(Comment.class));
        verify(commentMapper).toDto(comment);
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {
        Long taskId = 1L;
        CreateCommentDto request = new CreateCommentDto(
                "comment", "author", taskId);

        when(taskRepo.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> service.create(request));

        verify(taskRepo).findById(taskId);
        verify(commentRepo, never()).delete(any());
    }

    @Test
    void shouldDeleteComment() {
        Long id = 1L;
        Comment comment = new Comment();

        when(commentRepo.findById(id)).thenReturn(Optional.of(comment));

        service.delete(id);

        verify(commentRepo).findById(id);
        verify(commentRepo).delete(comment);
    }

    @Test
    void shouldThrowExceptionWhenCommentNotFound() {
        Long id = 1L;

        when(commentRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.delete(id));

        verify(commentRepo).findById(id);
        verify(commentRepo, never()).delete(any());
    }
}
