package com.marcinolek.mytimesheet.dto.user;

import com.marcinolek.mytimesheet.dto.base.AbstractDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Setter
@Getter
public class RoleDTO extends AbstractDTO implements GrantedAuthority {

    String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
