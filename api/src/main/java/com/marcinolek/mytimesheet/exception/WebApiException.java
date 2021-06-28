package com.marcinolek.mytimesheet.exception;

import com.marcinolek.mytimesheet.constants.exception.WebApiExceptionType;

public class WebApiException extends Exception{
    public WebApiException(WebApiExceptionType exceptionType) {
        super(exceptionType.toString());
    }
}
