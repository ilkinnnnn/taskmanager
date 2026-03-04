package io.github.ilkinnnnn.taskmanager.service;

import io.github.ilkinnnnn.taskmanager.dto.PageDto;
import io.github.ilkinnnnn.taskmanager.dto.comment.CommentDto;
import io.github.ilkinnnnn.taskmanager.dto.comment.CreateCommentDto;
import io.github.ilkinnnnn.taskmanager.dto.comment.UpdateCommentDto;
import io.github.ilkinnnnn.taskmanager.entity.Comment;
import io.github.ilkinnnnn.taskmanager.entity.Task;
import io.github.ilkinnnnn.taskmanager.exception.NotFoundException;
import io.github.ilkinnnnn.taskmanager.exception.task.TaskNotFoundException;
import io.github.ilkinnnnn.taskmanager.repository.CommentRepo;
import io.github.ilkinnnnn.taskmanager.repository.TaskRepo;
import io.github.ilkinnnnn.taskmanager.service.mapper.CommentMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {
    private CommentRepo commentRepo;
    private TaskRepo taskRepo;

    @Transactional
    public CommentDto create(CreateCommentDto dto) {
        Task parent = taskRepo.findById(dto.taskId()).orElseThrow(
                () -> new TaskNotFoundException(dto.taskId())
        );

        Comment comment = new Comment();
        comment.setComment(dto.comment());
        comment.setAuthor(dto.author());
        comment.setTask(parent);
        return CommentMapper.toDto(commentRepo.save(comment));
    }

    public PageDto<CommentDto> getAll(Long taskId, Pageable pageable) {
        Page<Comment> result;

        if (taskId != null) {
            result = commentRepo.getCommentsByTask_Id(taskId, pageable);
        } else {
            result = commentRepo.findAll(pageable);
        }

        return new PageDto<>(result.map(CommentMapper::toDto));
    }

    public CommentDto getById(Long id) {
        Comment comment = commentRepo.findById(id).orElseThrow(NotFoundException::new);
        return CommentMapper.toDto(comment);
    }

    @Transactional
    public CommentDto update(Long id, UpdateCommentDto dto) {
        Comment comment = commentRepo.findById(id).orElseThrow(NotFoundException::new);

        if (dto.comment() != null) comment.setComment(dto.comment());
        if (dto.author() != null) comment.setAuthor(dto.author());

        return CommentMapper.toDto(comment);
    }

    @Transactional
    public void delete(long id) {
        Comment comment = commentRepo.findById(id).orElseThrow(NotFoundException::new);
        commentRepo.delete(comment);
    }
}
