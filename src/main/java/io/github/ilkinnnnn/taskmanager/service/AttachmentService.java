package io.github.ilkinnnnn.taskmanager.service;

import io.github.ilkinnnnn.taskmanager.dto.PageDto;
import io.github.ilkinnnnn.taskmanager.dto.attachment.AttachmentDto;
import io.github.ilkinnnnn.taskmanager.dto.attachment.CreateAttachmentDto;
import io.github.ilkinnnnn.taskmanager.dto.attachment.UpdateAttachmentDto;
import io.github.ilkinnnnn.taskmanager.model.Attachment;
import io.github.ilkinnnnn.taskmanager.model.Task;
import io.github.ilkinnnnn.taskmanager.exception.NotFoundException;
import io.github.ilkinnnnn.taskmanager.exception.task.TaskNotFoundException;
import io.github.ilkinnnnn.taskmanager.repository.AttachmentRepo;
import io.github.ilkinnnnn.taskmanager.repository.TaskRepo;
import io.github.ilkinnnnn.taskmanager.mapper.AttachmentMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AttachmentService {
    private AttachmentRepo attachmentRepo;
    private TaskRepo taskRepo;
    private AttachmentMapper attachmentMapper;

    @Transactional
    public AttachmentDto create(CreateAttachmentDto dto) {
        Task parent = taskRepo.findById(dto.taskId()).orElseThrow(() -> new TaskNotFoundException(dto.taskId()));

        Attachment attachment = new Attachment();
        attachment.setFileName(dto.fileName());
        attachment.setFileUrl(dto.fileUrl());
        attachment.setFileType(dto.fileType());
        attachment.setTask(parent);
        return attachmentMapper.toDto(attachmentRepo.save(attachment));
    }

    public PageDto<AttachmentDto> getAll(Long taskId, Pageable pageable) {
        Page<Attachment> result;

        if (taskId != null) {
            result = attachmentRepo.getAttachmentsByTask_Id(taskId, pageable);
        } else {
            result = attachmentRepo.findAll(pageable);
        }

        return new PageDto<>(result.map(attachmentMapper::toDto));
    }

    public AttachmentDto getById(Long id) {
        Attachment attachment = attachmentRepo.findById(id).orElseThrow(NotFoundException::new);
        return attachmentMapper.toDto(attachment);
    }

    @Transactional
    public AttachmentDto update(Long id, UpdateAttachmentDto dto) {
        Attachment attachment = attachmentRepo.findById(id).orElseThrow(NotFoundException::new);

        if (dto.fileName() != null) attachment.setFileName(dto.fileName());
        if (dto.fileUrl() != null) attachment.setFileUrl(dto.fileUrl());
        if (dto.fileType() != null) attachment.setFileType(dto.fileType());

        return attachmentMapper.toDto(attachment);
    }

    @Transactional
    public void delete(long id) {
        Attachment attachment = attachmentRepo.findById(id).orElseThrow(NotFoundException::new);
        attachmentRepo.delete(attachment);
    }
}
