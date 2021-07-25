package com.marcinolek.mytimesheet.controller.base;

import com.marcinolek.mytimesheet.config.security.jwt.JwtTokenUtil;
import com.marcinolek.mytimesheet.constants.exception.WebApiExceptionType;
import com.marcinolek.mytimesheet.dto.message.ApiMessageDTO;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.infrastructure.provider.LoggedUserProvider;
import com.marcinolek.mytimesheet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @Autowired
    protected JwtTokenUtil jwtTokenUtil;

    @Autowired
    protected UserService userService;

    @Autowired
    protected LoggedUserProvider loggedUserProvider;

    protected Long getLoggedUserId() {
        return this.loggedUserProvider.getId();
    }

    protected String getLoggedUserUsername() {
        return this.loggedUserProvider.getUsername();
    }

    @ExceptionHandler(WebApiException.class)
    @ResponseBody
    public ResponseEntity<ApiMessageDTO> handleWebApiException(WebApiException e) {
        ApiMessageDTO apiMessage = new ApiMessageDTO(e.getMessage(), e.getExceptionLevel(), e.isShowMessage());
        HttpStatus httpStatus = WebApiExceptionType.getHttpStatus(e.getExceptionType());
        return new ResponseEntity<>(apiMessage, httpStatus);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ApiMessageDTO handleUsernameNotFoundException(UsernameNotFoundException e) {
        return new ApiMessageDTO(e.getMessage());
    }

}
