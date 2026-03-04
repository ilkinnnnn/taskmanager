package io.github.ilkinnnnn.taskmanager.service;


import io.github.ilkinnnnn.taskmanager.dto.PageDto;
import io.github.ilkinnnnn.taskmanager.dto.task.CreateTaskDto;
import io.github.ilkinnnnn.taskmanager.dto.task.TaskDto;
import io.github.ilkinnnnn.taskmanager.dto.task.TaskWithAttachmentsDto;
import io.github.ilkinnnnn.taskmanager.dto.task.UpdateTaskDto;
import io.github.ilkinnnnn.taskmanager.entity.Task;
import io.github.ilkinnnnn.taskmanager.entity.TaskStatus;
import io.github.ilkinnnnn.taskmanager.exception.task.TaskNotFoundException;
import io.github.ilkinnnnn.taskmanager.repository.TaskRepo;
import io.github.ilkinnnnn.taskmanager.service.mapper.TaskMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepo taskRepo;

    public TaskDto create(CreateTaskDto dto) {
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setDueDate(dto.dueDate());
        return TaskMapper.toDto(taskRepo.save(task));
    }


    public PageDto<TaskDto> getAll(TaskStatus status, Pageable pageable) {
        Page<Task> result;

        if (status != null) {
            result = taskRepo.findAllByStatus(status, pageable);
        } else {
            result = taskRepo.findAll(pageable);
        }

        return new PageDto<>(result.map(TaskMapper::toDto));

    }

    public TaskWithAttachmentsDto getById(Long id) {
        Task task = taskRepo.findWithAttachmentsById(id).orElseThrow(() ->new TaskNotFoundException(id));
        return TaskMapper.toDtoWithAttachments(task);
    }

    @Transactional
    public void deleteById(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() ->new TaskNotFoundException(id));
        taskRepo.delete(task);
    }

    @Transactional
    public TaskDto update(Long id, UpdateTaskDto dto) {
        Task task = taskRepo.findById(id).orElseThrow(() ->new TaskNotFoundException(id));

        if(dto.title() != null) task.setTitle(dto.title());
        if(dto.description() != null) task.setDescription(dto.description());
        if(dto.dueDate() != null) task.setDueDate(dto.dueDate());
        if(dto.status() != null) task.setStatus(dto.status());

        return TaskMapper.toDto(task);
    }

    public TaskDto patchStatus(Long id, TaskStatus status) {
        Task task = taskRepo.findById(id).orElseThrow(() ->new TaskNotFoundException(id));
        task.setStatus(status);
        return TaskMapper.toDto(taskRepo.save(task));
    }
}
