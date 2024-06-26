package com.marcinolek.mytimesheet.controller.user;

import com.marcinolek.mytimesheet.constants.permission.RoleType;
import com.marcinolek.mytimesheet.controller.base.AbstractCrudController;
import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractCrudController<UserEntity, UserDTO, Long> {

    @Override
    protected void checkCreatePermission() throws WebApiException {
        this.isHasRole(RoleType.ADMIN);
    }

    @Override
    protected void checkReadPermission() throws WebApiException {
        this.isHasRole(RoleType.ADMIN);
    }

    @Override
    protected void checkUpdatePermission() throws WebApiException {
        this.isHasRole(RoleType.ADMIN);
    }

    @Override
    protected void checkDeletePermission() throws WebApiException {
        this.isHasRole(RoleType.ADMIN);
    }
}
