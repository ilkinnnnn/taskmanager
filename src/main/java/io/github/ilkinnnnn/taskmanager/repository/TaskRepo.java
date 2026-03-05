package io.github.ilkinnnnn.taskmanager.repository;


import io.github.ilkinnnnn.taskmanager.model.Task;
import io.github.ilkinnnnn.taskmanager.model.enums.TaskStatus;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<@NonNull Task,@NonNull Long> {

    Page<@NonNull Task> findAllByStatus(TaskStatus status, Pageable pageable);

    @EntityGraph(value = "task.withAttachments", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Task> findWithAttachmentsById(Long id);
}
