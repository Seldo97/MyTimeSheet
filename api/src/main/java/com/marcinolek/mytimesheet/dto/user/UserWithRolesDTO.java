package com.marcinolek.mytimesheet.dto.user;

import com.marcinolek.mytimesheet.dto.base.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserWithRolesDTO extends AbstractDTO {

    String name;

    String lastname;

    String email;

    String username;

    List<RoleDTO> roles;
}
