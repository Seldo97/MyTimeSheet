package com.marcinolek.mytimesheet.dto.user;

import com.marcinolek.mytimesheet.dto.base.BaseExtendedDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithPasswordDTO extends BaseExtendedDTO {

    String name;

    String lastname;

    String email;

    String username;

    String password;

    List<UserGroupDTO> userGroups;

}
