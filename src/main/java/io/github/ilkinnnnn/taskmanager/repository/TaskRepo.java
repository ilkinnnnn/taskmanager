package io.github.ilkinnnnn.taskmanager.repository;


import io.github.ilkinnnnn.taskmanager.entity.Task;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<@NonNull Task,@NonNull Long> {

}
