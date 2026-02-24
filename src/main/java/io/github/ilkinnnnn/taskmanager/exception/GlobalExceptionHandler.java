package io.github.ilkinnnnn.taskmanager.exception;

import org.jspecify.annotations.NonNull;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.core.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ PropertyReferenceException.class,
            MethodArgumentTypeMismatchException.class,
            InvalidDataAccessApiUsageException.class })
    public ResponseEntity<@NonNull String> handleInvalidSort() {
        String message = "Invalid url";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
