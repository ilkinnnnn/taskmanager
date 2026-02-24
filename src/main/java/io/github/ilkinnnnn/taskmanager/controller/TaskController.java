package io.github.ilkinnnnn.taskmanager.controller;

import io.github.ilkinnnnn.taskmanager.dto.PageDto;
import io.github.ilkinnnnn.taskmanager.dto.task.CreateTaskDto;
import io.github.ilkinnnnn.taskmanager.dto.task.UpdateTaskDto;
import io.github.ilkinnnnn.taskmanager.entity.Task;
import io.github.ilkinnnnn.taskmanager.entity.TaskStatus;
import io.github.ilkinnnnn.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
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
    public Task createTask(@RequestBody @Valid CreateTaskDto dto) {
        return taskService.create(dto);
    }

    @GetMapping
    public PageDto<@NonNull Task> getTasks(@RequestParam(required = false) TaskStatus status, Pageable pageable) {
        Page<@NonNull Task> page = taskService.getAll(status, pageable);
        return new PageDto<>(page);
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody @Valid UpdateTaskDto dto) {
        return taskService.update(id, dto);
    }

    @PatchMapping("/{id}/status")
    public Task patchStatus(@PathVariable Long id, @RequestBody TaskStatus status) {
        return taskService.patchStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}
