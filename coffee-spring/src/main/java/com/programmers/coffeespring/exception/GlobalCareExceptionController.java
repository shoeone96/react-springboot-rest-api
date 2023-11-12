package com.programmers.coffeespring.exception;

import com.programmers.coffeespring.dto.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalCareExceptionController {

    @ExceptionHandler(CafeException.class)
    public ResponseEntity<?> applicationHandler(CafeException e){
        log.error("error {}", e.getMessage());
        return ResponseEntity.status(
                        e.getErrorCode().getStatus())
                .body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> applicationHandler(RuntimeException e){
        log.error("error {}", e.getMessage());
        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(Response.error(ErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        log.error("error {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorCode.INVALID_INPUT.getErrorMessage());
    }
}
