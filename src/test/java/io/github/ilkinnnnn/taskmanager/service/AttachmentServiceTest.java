package io.github.ilkinnnnn.taskmanager.service;

import io.github.ilkinnnnn.taskmanager.dto.attachment.AttachmentDto;
import io.github.ilkinnnnn.taskmanager.dto.attachment.CreateAttachmentDto;
import io.github.ilkinnnnn.taskmanager.exception.NotFoundException;
import io.github.ilkinnnnn.taskmanager.exception.task.TaskNotFoundException;
import io.github.ilkinnnnn.taskmanager.mapper.AttachmentMapper;
import io.github.ilkinnnnn.taskmanager.model.Attachment;
import io.github.ilkinnnnn.taskmanager.model.Task;
import io.github.ilkinnnnn.taskmanager.model.enums.AttachmentFileType;
import io.github.ilkinnnnn.taskmanager.repository.AttachmentRepo;
import io.github.ilkinnnnn.taskmanager.repository.TaskRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AttachmentServiceTest {
    @Mock
    AttachmentRepo attachmentRepo;

    @Mock
    TaskRepo taskRepo;

    @Mock
    AttachmentMapper attachmentMapper;

    @InjectMocks
    AttachmentService service;

    @Test
    void shouldCreateAttachment() {
        Long taskId = 1L;
        CreateAttachmentDto request = new CreateAttachmentDto(
                "name", "url", AttachmentFileType.TEXT, taskId);

        AttachmentDto response = new AttachmentDto(
                1L, "name", "url", AttachmentFileType.TEXT, taskId);

        Task task = new Task();
        Attachment attachment = new Attachment();
        attachment.setFileName("name");
        attachment.setFileUrl("url");
        attachment.setFileType(AttachmentFileType.TEXT);

        when(taskRepo.findById(taskId)).thenReturn(Optional.of(task));
        when(attachmentRepo.save(any(Attachment.class))).thenReturn(attachment);
        when(attachmentMapper.toDto(attachment)).thenReturn(response);

        AttachmentDto result = service.create(request);
        assertEquals(request.fileName(), result.fileName());
        assertEquals(request.fileUrl(), result.fileUrl());
        assertEquals(request.fileType(), result.fileType());
        assertEquals(request.taskId(), result.taskId());

        verify(taskRepo).findById(taskId);
        verify(attachmentRepo).save(any(Attachment.class));
        verify(attachmentMapper).toDto(attachment);

    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {
        Long taskId = 1L;
        CreateAttachmentDto request = new CreateAttachmentDto(
                "name", "url", AttachmentFileType.TEXT, taskId);

        when(taskRepo.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> service.create(request));

        verify(taskRepo).findById(taskId);
        verify(attachmentRepo, never()).delete(any());
    }

    @Test
    void shouldDeleteAttachment() {
        Long id = 1L;
        Attachment attachment = new Attachment();

        when(attachmentRepo.findById(id)).thenReturn(Optional.of(attachment));

        service.delete(id);

        verify(attachmentRepo).findById(id);
        verify(attachmentRepo).delete(attachment);
    }

    @Test
    void shouldThrowExceptionWhenAttachmentNotFound() {
        Long id = 1L;

        when(attachmentRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.delete(id));

        verify(attachmentRepo).findById(id);
        verify(attachmentRepo, never()).delete(any());
    }
}
