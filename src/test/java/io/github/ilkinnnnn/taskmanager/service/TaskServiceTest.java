package io.github.ilkinnnnn.taskmanager.service;

import io.github.ilkinnnnn.taskmanager.dto.task.CreateTaskDto;
import io.github.ilkinnnnn.taskmanager.dto.task.TaskDto;
import io.github.ilkinnnnn.taskmanager.exception.task.TaskNotFoundException;
import io.github.ilkinnnnn.taskmanager.mapper.TaskMapper;
import io.github.ilkinnnnn.taskmanager.model.Task;
import io.github.ilkinnnnn.taskmanager.model.enums.TaskStatus;
import io.github.ilkinnnnn.taskmanager.repository.TaskRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock
    private TaskRepo taskRepo;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService service;

    @Test
    void shouldCreateTask() {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        LocalDate dueDate = LocalDate.now();


        CreateTaskDto request = new CreateTaskDto("test", "test", dueDate);
        TaskDto response = new TaskDto(
                1L, "test", "test",
                TaskStatus.NEW, dueDate, createdAt, updatedAt
        );

        Task savedTask = new Task();
        savedTask.setTitle("test");
        savedTask.setDescription("test");
        savedTask.setDueDate(dueDate);

        when(taskRepo.save(any(Task.class))).thenReturn(savedTask);
        when(taskMapper.toDto(savedTask)).thenReturn(response);

        TaskDto result = service.create(request);

        assertEquals(request.title(), result.title());
        assertEquals(request.description(), result.description());
        assertEquals(request.dueDate(), result.dueDate());

        verify(taskRepo).save(any(Task.class));
        verify(taskMapper).toDto(savedTask);
    }

    @Test
    void shouldDeleteTask() {
        Long id = 1L;
        Task task = new Task();

        when(taskRepo.findById(id)).thenReturn(Optional.of(task));

        service.deleteById(id);

        verify(taskRepo).findById(id);
        verify(taskRepo).delete(task);
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {
        Long id = 1L;

        when(taskRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> service.deleteById(id));

        verify(taskRepo).findById(id);
        verify(taskRepo, never()).delete(any());
    }
}