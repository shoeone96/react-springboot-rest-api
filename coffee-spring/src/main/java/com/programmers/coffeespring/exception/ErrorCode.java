package com.programmers.coffeespring.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 에러가 발생했습니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "잘못 입력하였습니다. 다시 입력해주세요"),
    IMG_SAVE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 저장 중 문제가 발생했습니다."),
    COMPRESS_IMG_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 저장을 위한 압축 과정에서 에러가 발생했습니다."),
    CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 고객을 찾을 수 없습니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "선택하신 상품 정보를 찾을 수 없습니다."),
    INVALID_TOTAL_PRICE(HttpStatus.BAD_REQUEST, "잘못된 총합 계산입니다."),
    VOUCHER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 바우처를 찾을 수 없습니다."),
    VOUCHER_NOT_VALID(HttpStatus.BAD_REQUEST, "해당 바우처는 사용이 불가합니다.");
    private final HttpStatus status;
    private final String errorMessage;
}
