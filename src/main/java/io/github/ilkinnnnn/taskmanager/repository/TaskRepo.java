package io.github.ilkinnnnn.taskmanager.repository;


import io.github.ilkinnnnn.taskmanager.entity.Task;
import io.github.ilkinnnnn.taskmanager.entity.TaskStatus;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<@NonNull Task,@NonNull Long> {

    Page<@NonNull Task> findAllByStatus(TaskStatus status, Pageable pageable);
}
