package com.marcinolek.mytimesheet.controller.user;

import com.marcinolek.mytimesheet.controller.base.AbstractCrudController;
import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.dto.user.UserWithGroupsDTO;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractCrudController<UserEntity, UserDTO, Long> {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}/with-groups")
    public UserWithGroupsDTO getByIdWithGroups(@PathVariable Long id) throws WebApiException {
        return this.userService.findByIdWithGroups(id);
    }

}
