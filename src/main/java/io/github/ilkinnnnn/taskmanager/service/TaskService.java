package io.github.ilkinnnnn.taskmanager.service;


import io.github.ilkinnnnn.taskmanager.dto.task.CreateTaskDto;
import io.github.ilkinnnnn.taskmanager.dto.task.UpdateTaskDto;
import io.github.ilkinnnnn.taskmanager.entity.Task;
import io.github.ilkinnnnn.taskmanager.exception.task.TaskNotFoundException;
import io.github.ilkinnnnn.taskmanager.repository.TaskRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task create(CreateTaskDto dto) {
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setDueDate(dto.dueDate());
        return taskRepo.save(task);
    }

    public List<Task> getAll() {
        return taskRepo.findAll();
    }

    public Task getById(Long id) {
        return taskRepo.findById(id).orElseThrow(() ->new TaskNotFoundException(id));
    }

    public void deleteById(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() ->new TaskNotFoundException(id));
        taskRepo.delete(task);
    }

    @Transactional
    public Task update(Long id, UpdateTaskDto dto) {
        Task task = taskRepo.findById(id).orElseThrow(() ->new TaskNotFoundException(id));

        if(dto.title() != null) task.setTitle(dto.title());
        if(dto.description() != null) task.setDescription(dto.description());
        if(dto.dueDate() != null) task.setDueDate(dto.dueDate());
        if(dto.status() != null) task.setStatus(dto.status());

        return task;
    }
}
