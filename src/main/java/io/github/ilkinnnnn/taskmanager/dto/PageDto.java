package io.github.ilkinnnnn.taskmanager.dto;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;

import java.util.List;

public record PageDto<T>(List<T> content, long totalElements, int page, int size) {
    public PageDto (Page<@NonNull T> page) {
        this(
                page.getContent(),
                page.getTotalElements(),
                page.getNumber(),
                page.getSize()
        );
    }
}
