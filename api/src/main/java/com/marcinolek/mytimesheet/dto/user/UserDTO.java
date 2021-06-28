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
public class UserDTO extends BaseExtendedDTO {

    String name;

    String lastname;

    String email;

    String username;

    List<UserGroupDTO> userGroups;

}
