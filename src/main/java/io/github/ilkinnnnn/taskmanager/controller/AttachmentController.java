package io.github.ilkinnnnn.taskmanager.controller;

import io.github.ilkinnnnn.taskmanager.dto.PageDto;
import io.github.ilkinnnnn.taskmanager.dto.attachment.AttachmentDto;
import io.github.ilkinnnnn.taskmanager.dto.attachment.CreateAttachmentDto;
import io.github.ilkinnnnn.taskmanager.dto.attachment.UpdateAttachmentDto;
import io.github.ilkinnnnn.taskmanager.service.AttachmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/attachments")
@AllArgsConstructor
public class AttachmentController {
    private AttachmentService attachmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AttachmentDto createAttachment(@RequestBody @Valid CreateAttachmentDto dto) {
        return attachmentService.create(dto);
    }

    @GetMapping
    public PageDto<AttachmentDto> getAttachments(@RequestParam(required = false) Long taskId, Pageable pageable) {
        return attachmentService.getAll(taskId, pageable);
    }

    @GetMapping("/{id}")
    public AttachmentDto getAttachmentById(@PathVariable Long id) {
        return attachmentService.getById(id);
    }

    @PutMapping("/{id}")
    public AttachmentDto updateAttachment(@PathVariable Long id, @RequestBody @Valid UpdateAttachmentDto dto) {
        return attachmentService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        attachmentService.delete(id);
    }
}
