package com.marcinolek.mytimesheet.exception;

import com.marcinolek.mytimesheet.constants.exception.ExceptionLevel;
import com.marcinolek.mytimesheet.constants.exception.WebApiExceptionType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WebApiException extends Exception{

    private WebApiExceptionType exceptionType;
    private ExceptionLevel exceptionLevel;
    private boolean showMessage;

    public WebApiException(WebApiExceptionType exceptionType) {
        super(exceptionType.toString());
        this.exceptionType = exceptionType;
        this.exceptionLevel = ExceptionLevel.ERROR;
        this.showMessage = true;
    }

    public WebApiException(WebApiExceptionType exceptionType, ExceptionLevel exceptionLevel) {
        super(exceptionType.toString());
        this.exceptionType = exceptionType;
        this.exceptionLevel = exceptionLevel;
        this.showMessage = true;
    }

    public WebApiException(WebApiExceptionType exceptionType, ExceptionLevel exceptionLevel, boolean showMessage) {
        super(exceptionType.toString());
        this.exceptionType = exceptionType;
        this.exceptionLevel = exceptionLevel;
        this.showMessage = showMessage;
    }
}
