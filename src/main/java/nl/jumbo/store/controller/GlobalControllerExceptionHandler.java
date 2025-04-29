package nl.jumbo.store.controller;

import lombok.extern.slf4j.Slf4j;
import nl.jumbo.store.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentTypeMismatchException(Exception e) {
        log.info("User has supplied invalid input data", e);
        return ResponseEntity.badRequest().body(new ErrorDto(e.getMessage()));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDto> handleRuntimeException(Exception e) {
        log.error("Unexpected exception occurred", e);
        return ResponseEntity.internalServerError().body(new ErrorDto(e.getMessage()));
    }
}