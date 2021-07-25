package com.marcinolek.mytimesheet.controller.user;

import com.marcinolek.mytimesheet.controller.base.AbstractCrudController;
import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractCrudController<UserEntity, UserDTO, Long> {
}
