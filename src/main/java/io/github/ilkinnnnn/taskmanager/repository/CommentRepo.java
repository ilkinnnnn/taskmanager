package io.github.ilkinnnnn.taskmanager.repository;

import io.github.ilkinnnnn.taskmanager.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    Page<Comment> getCommentsByTask_Id(Long taskId, Pageable pageable);
}
