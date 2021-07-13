package com.marcinolek.mytimesheet.dto.user;

import com.marcinolek.mytimesheet.dto.base.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissionDTO extends AbstractDTO {
    private String category;
    private String name;
}
