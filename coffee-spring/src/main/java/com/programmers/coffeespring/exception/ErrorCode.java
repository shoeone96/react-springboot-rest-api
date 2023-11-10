package com.programmers.coffeespring.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 에러가 발생했습니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "잘못 입력하였습니다. 다시 입력해주세요"),
    IMG_SAVE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 저장 중 문제가 발생했습니다.");
    private final HttpStatus status;
    private final String errorMessage;
}
