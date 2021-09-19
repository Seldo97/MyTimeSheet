package com.marcinolek.mytimesheet.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcinolek.mytimesheet.config.security.jwt.JwtTokenUtil;
import com.marcinolek.mytimesheet.dto.auth.AuthRequestDTO;
import com.marcinolek.mytimesheet.dto.message.ApiMessageDTO;
import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.exception.WebApiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class AuthenticationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void generateJwtTokenTest() throws WebApiException {
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
        authRequest.setUsername("Tester2");
        authRequest.setPassword("test123");

        ObjectMapper objectMapper = new ObjectMapper();
        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", StandardCharsets.UTF_8);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                .content(objectMapper.writeValueAsString(authRequest))
                .contentType(MEDIA_TYPE_JSON_UTF8)
        ).andExpect(status().isOk()).andReturn();
        result.getResponse().getHeader(HttpHeaders.AUTHORIZATION);
        String token = result.getResponse().getHeader(HttpHeaders.AUTHORIZATION);

        Assertions.assertTrue(jwtTokenUtil.validate(token));
        Assertions.assertEquals(jwtTokenUtil.getUsername(token), "tester2");
        Assertions.assertEquals(jwtTokenUtil.getUserId(token), -3L);

        mvc.perform(MockMvcRequestBuilders.get("/api/auth/is-authenticated")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/api/auth/is-common-user")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/api/auth/is-premium-user")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isForbidden());

        mvc.perform(MockMvcRequestBuilders.get("/api/auth/is-admin")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isForbidden());
    }

    @Test
    public void failAuthenticationUserTest() throws Exception {

        AuthRequestDTO authRequest = new AuthRequestDTO();
        authRequest.setUsername("dasdas34234dsadasdsa324dsadsa2vcxzw13sad56zxc4bvcb");
        authRequest.setPassword("adasdsa213dsa123sda14bncvbn24sada3412samin");

        ObjectMapper objectMapper = new ObjectMapper();
        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", StandardCharsets.UTF_8);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                .content(objectMapper.writeValueAsString(authRequest))
                .contentType(MEDIA_TYPE_JSON_UTF8)
        ).andExpect(status().isBadRequest()).andReturn();
        result.getResponse().getHeader(HttpHeaders.AUTHORIZATION);
        String token = result.getResponse().getHeader(HttpHeaders.AUTHORIZATION);
        ApiMessageDTO apiMessage = objectMapper.readValue(result.getResponse().getContentAsString(), ApiMessageDTO.class);

        Assertions.assertNull(token);
        Assertions.assertEquals(apiMessage.getExceptionType(), "BAD_CREDENTIALS");

        mvc.perform(MockMvcRequestBuilders.get("/api/auth/authenticated"))
                .andExpect(status().isUnauthorized());
    }

}
