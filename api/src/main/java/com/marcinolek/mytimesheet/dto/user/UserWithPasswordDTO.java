package com.marcinolek.mytimesheet.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithPasswordDTO extends UserDTO {

    String password;

    List<RoleDTO> roles;

}
