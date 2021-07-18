package com.marcinolek.mytimesheet.dto.message;

import com.marcinolek.mytimesheet.constants.exception.ExceptionLevel;
import com.marcinolek.mytimesheet.constants.exception.WebApiExceptionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ApiMessageDTO {

    private String exceptionType;
    private ExceptionLevel exceptionLevel;
    private boolean showMessage;

    public ApiMessageDTO(String exceptionType) {
        this.exceptionType = exceptionType.toString();
        this.exceptionLevel = ExceptionLevel.ERROR;
        this.showMessage = true;
    }

    public ApiMessageDTO(String exceptionType, ExceptionLevel exceptionLevel) {
        this.exceptionType = exceptionType.toString();
        this.exceptionLevel = exceptionLevel;
        this.showMessage = true;
    }

    public ApiMessageDTO(String exceptionType, ExceptionLevel exceptionLevel, boolean showMessage) {
        this.exceptionType = exceptionType.toString();
        this.exceptionLevel = exceptionLevel;
        this.showMessage = showMessage;
    }

}
