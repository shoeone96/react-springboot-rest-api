package com.programmers.coffeespring.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class CafeException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String message;

    public CafeException(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.message = errorCode.getErrorMessage();
    }
}
