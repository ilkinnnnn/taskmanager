package io.github.ilkinnnnn.taskmanager.repository;

import io.github.ilkinnnnn.taskmanager.entity.Attachment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepo extends JpaRepository<Attachment, Long> {
    Page<Attachment> getAttachmentsByTask_Id(Long taskId, Pageable pageable);
}
