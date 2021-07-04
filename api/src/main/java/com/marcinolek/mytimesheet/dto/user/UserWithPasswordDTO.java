package com.marcinolek.mytimesheet.dto.user;

import com.marcinolek.mytimesheet.dto.base.AbstractExtendedDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithPasswordDTO extends AbstractExtendedDTO {

    String name;

    String lastname;

    String email;

    String username;

    String password;

}
