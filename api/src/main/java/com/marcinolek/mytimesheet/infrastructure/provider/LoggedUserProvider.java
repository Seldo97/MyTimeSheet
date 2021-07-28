package com.marcinolek.mytimesheet.infrastructure.provider;

import com.marcinolek.mytimesheet.config.security.jwt.JwtTokenUtil;
import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoggedUserProvider {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String getUsername() {
        return jwtTokenUtil.getUsername(request.getHeader("Authorization"));
    }

    public Long getId() {
        return jwtTokenUtil.getUserId(request.getHeader("Authorization"));
    }

    public UserDTO getUser() throws WebApiException {
        return this.userService.findById(this.getId());
    }

}
