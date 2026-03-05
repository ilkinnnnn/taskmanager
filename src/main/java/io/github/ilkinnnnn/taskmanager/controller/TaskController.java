package io.github.ilkinnnnn.taskmanager.controller;

import io.github.ilkinnnnn.taskmanager.dto.PageDto;
import io.github.ilkinnnnn.taskmanager.dto.task.CreateTaskDto;
import io.github.ilkinnnnn.taskmanager.dto.task.TaskDto;
import io.github.ilkinnnnn.taskmanager.dto.task.TaskWithAttachmentsDto;
import io.github.ilkinnnnn.taskmanager.dto.task.UpdateTaskDto;
import io.github.ilkinnnnn.taskmanager.model.enums.TaskStatus;
import io.github.ilkinnnnn.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@RequestBody @Valid CreateTaskDto dto) {
        return taskService.create(dto);
    }

    @GetMapping
    public PageDto<TaskDto> getTasks(@RequestParam(required = false) TaskStatus status, Pageable pageable) {
        return taskService.getAll(status, pageable);
    }

    @GetMapping("/{id}")
    public TaskWithAttachmentsDto getTaskById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable Long id, @RequestBody @Valid UpdateTaskDto dto) {
        return taskService.update(id, dto);
    }

    @PatchMapping("/{id}/status")
    public TaskDto patchStatus(@PathVariable Long id, @RequestBody TaskStatus status) {
        return taskService.patchStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}
