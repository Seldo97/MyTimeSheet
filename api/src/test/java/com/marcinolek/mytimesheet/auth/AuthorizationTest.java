package com.marcinolek.mytimesheet.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcinolek.mytimesheet.config.security.jwt.JwtTokenUtil;
import com.marcinolek.mytimesheet.controller.auth.AuthController;
import com.marcinolek.mytimesheet.dto.auth.AuthRequestDTO;
import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.exception.WebApiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class AuthorizationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void generateJwtTokenTest() {
        UserDTO user = new UserDTO();
        user.setId(-99L);
        user.setUsername("xxxXXxxxTestXxxxXxx");
        String token = jwtTokenUtil.generateToken(user);

        Assertions.assertTrue(jwtTokenUtil.validate(token));
        Assertions.assertEquals(jwtTokenUtil.getUsername(token), "xxxXXxxxTestXxxxXxx");
        Assertions.assertEquals(jwtTokenUtil.getUserId(token), -99L);
        assertThat(jwtTokenUtil.getExpirationDate(token)).isAfter(new Date());
    }

    @Test
    public void authenticationUserTest() throws Exception {
        AuthRequestDTO authRequest = new AuthRequestDTO();
        authRequest.setUsername("admin");
        authRequest.setPassword("admin");

        ObjectMapper objectMapper = new ObjectMapper();
        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", StandardCharsets.UTF_8);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                .content(objectMapper.writeValueAsString(authRequest))
                .locale(Locale.ENGLISH)
                .accept(MEDIA_TYPE_JSON_UTF8)
                .contentType(MEDIA_TYPE_JSON_UTF8)
        ).andExpect(status().isOk()).andReturn();
        result.getResponse().getHeader(HttpHeaders.AUTHORIZATION);
        String token = result.getResponse().getHeader(HttpHeaders.AUTHORIZATION);

        Assertions.assertTrue(jwtTokenUtil.validate(token));
        Assertions.assertEquals(jwtTokenUtil.getUsername(token), "admin");
        Assertions.assertEquals(jwtTokenUtil.getUserId(token), -1L);

        mvc.perform(MockMvcRequestBuilders.get("/api/auth/authenticated")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void failAuthenticationUserTest() throws Exception {
        // TODO exception handler controller

//        AuthRequestDTO authRequest = new AuthRequestDTO();
//        authRequest.setUsername("dasdas34234dsadasdsa324dsadsa2vcxzw13sad56zxc4bvcb");
//        authRequest.setPassword("adasdsa213dsa123sda14bncvbn24sada3412samin");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", StandardCharsets.UTF_8);
//        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
//                .content(objectMapper.writeValueAsString(authRequest))
//                .locale(Locale.ENGLISH)
//                .accept(MEDIA_TYPE_JSON_UTF8)
//                .contentType(MEDIA_TYPE_JSON_UTF8)
//        ).andExpect(WebApiException.class)
//        result.getResponse().getHeader(HttpHeaders.AUTHORIZATION);
//        String token = result.getResponse().getHeader(HttpHeaders.AUTHORIZATION);
//
//        Assertions.assertNull(token);
//
        mvc.perform(MockMvcRequestBuilders.get("/api/auth/authenticated"))
                .andExpect(status().isUnauthorized());
    }

}
