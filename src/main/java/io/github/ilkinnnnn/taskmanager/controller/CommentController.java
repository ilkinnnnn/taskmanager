package io.github.ilkinnnnn.taskmanager.controller;


import io.github.ilkinnnnn.taskmanager.dto.PageDto;
import io.github.ilkinnnnn.taskmanager.dto.comment.CommentDto;
import io.github.ilkinnnnn.taskmanager.dto.comment.CreateCommentDto;
import io.github.ilkinnnnn.taskmanager.dto.comment.UpdateCommentDto;
import io.github.ilkinnnnn.taskmanager.service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comments")
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@RequestBody @Valid CreateCommentDto dto) {
        return commentService.create(dto);
    }

    @GetMapping
    public PageDto<CommentDto> getComments(@RequestParam(required = false) Long taskId, Pageable pageable) {
        return commentService.getAll(taskId, pageable);
    }

    @GetMapping("/{id}")
    public CommentDto getCommentById(@PathVariable Long id) {
        return commentService.getById(id);
    }

    @PutMapping("/{id}")
    public CommentDto updateComment(@PathVariable Long id, @RequestBody @Valid UpdateCommentDto dto) {
        return commentService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        commentService.delete(id);
    }
}