package com.programmers.coffeespring.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CafeException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String message;
}
