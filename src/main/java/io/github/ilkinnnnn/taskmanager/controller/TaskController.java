package io.github.ilkinnnnn.taskmanager.controller;

import io.github.ilkinnnnn.taskmanager.dto.task.CreateTaskDto;
import io.github.ilkinnnnn.taskmanager.dto.task.UpdateTaskDto;
import io.github.ilkinnnnn.taskmanager.entity.Task;
import io.github.ilkinnnnn.taskmanager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody CreateTaskDto dto) {
        return taskService.create(dto);
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody UpdateTaskDto dto) {
        return taskService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}
