package com.marcinolek.mytimesheet.controller.base;

import com.marcinolek.mytimesheet.config.security.jwt.JwtTokenUtil;
import com.marcinolek.mytimesheet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    protected JwtTokenUtil jwtTokenUtil;

    @Autowired
    protected UserService userService;

//    protected

}
