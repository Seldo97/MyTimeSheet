package com.marcinolek.mytimesheet.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequestDTO {
    private String username;
    private String password;
}
