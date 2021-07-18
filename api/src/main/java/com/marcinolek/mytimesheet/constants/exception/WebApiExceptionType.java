package com.marcinolek.mytimesheet.constants.exception;

import org.springframework.http.HttpStatus;

public enum WebApiExceptionType {
    USER_UNAUTHORIZED,
    BAD_CREDENTIALS,
    NO_PERMISSION_TO_RESOURCE,
    ENTITY_NOT_FOUND,
    SAVE_FAILED,
    DELETE_FAILED,
    USER_NOT_FOUND;

    public static HttpStatus getHttpStatus(WebApiExceptionType exceptionType) {
        switch (exceptionType) {
            case USER_UNAUTHORIZED:
                return HttpStatus.UNAUTHORIZED;
            default:
                return HttpStatus.BAD_REQUEST;
        }
    }
}
