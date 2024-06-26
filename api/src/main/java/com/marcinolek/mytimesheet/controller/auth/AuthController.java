package com.marcinolek.mytimesheet.controller.auth;

import com.marcinolek.mytimesheet.config.security.jwt.JwtTokenUtil;
import com.marcinolek.mytimesheet.constants.exception.WebApiExceptionType;
import com.marcinolek.mytimesheet.constants.permission.RoleType;
import com.marcinolek.mytimesheet.controller.base.BaseController;
import com.marcinolek.mytimesheet.dto.auth.AuthRequestDTO;
import com.marcinolek.mytimesheet.dto.jwt.BlockedJwtDTO;
import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.service.jwt.BlockedJwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api/auth")
@RestController
public class AuthController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BlockedJwtService blockedJwtService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO login(@RequestBody AuthRequestDTO request, HttpServletResponse response) throws WebApiException {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            UserDTO user = this.userService.getUserByUsername(request.getUsername());
            response.addHeader(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateToken(user));

            return user;
        } catch (BadCredentialsException ex) {
            logger.error(ex.getMessage());
            throw new WebApiException(WebApiExceptionType.BAD_CREDENTIALS);
        }
    }

    @GetMapping("/logout")
    public BlockedJwtDTO logout(@RequestHeader(value = "Authorization", required = false) String token) throws WebApiException {
       return this.blockedJwtService.blockToken(JwtTokenUtil.getClearToken(token));
    }

    @GetMapping("/is-authenticated")
    public boolean isUserAuthenticated() { return true; }

    @GetMapping("/is-admin")
    @Secured(RoleType.ADMIN)
    public boolean isUserAdmin() {
        return true;
    }

    @GetMapping("/is-common-user")
    @Secured(RoleType.COMMON_USER)
    public boolean isUserCommonUser() {
        return true;
    }

    @GetMapping("/is-premium-user")
    @Secured(RoleType.PREMIUM_USER)
    public boolean isUserPremiumUser() {
        return true;
    }

    protected boolean isHasRole(String role) throws WebApiException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(role))) {
            return true;
        } else {
            throw new WebApiException(WebApiExceptionType.NO_PERMISSION_TO_RESOURCE);
        }
    }

}
