package com.marcinolek.mytimesheet.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserWithRolesDTO extends UserDTO {

    List<RoleDTO> roles;

}
