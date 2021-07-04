package com.marcinolek.mytimesheet.dto.user;

import com.marcinolek.mytimesheet.dto.base.AbstractExtendedDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserGroupDTO extends AbstractExtendedDTO {
    String name;
}