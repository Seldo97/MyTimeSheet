package com.marcinolek.mytimesheet.dto.user;

import com.marcinolek.mytimesheet.dto.base.AbstractExtendedDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserWithGroupsDTO extends AbstractExtendedDTO {
    String name;

    String lastname;

    String email;

    String username;

    List<UserGroupDTO> userGroups;
}
