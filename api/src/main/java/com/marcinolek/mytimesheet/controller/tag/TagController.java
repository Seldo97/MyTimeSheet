package com.marcinolek.mytimesheet.controller.tag;

import com.marcinolek.mytimesheet.constants.permission.RoleType;
import com.marcinolek.mytimesheet.controller.base.AbstractCrudController;
import com.marcinolek.mytimesheet.dto.tag.TagDTO;
import com.marcinolek.mytimesheet.entity.tag.TagEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tag")
public class TagController extends AbstractCrudController<TagEntity, TagDTO, Long> {

    @Override
    protected void checkCreatePermission() throws WebApiException {
        this.isHasRole(RoleType.COMMON_USER);
    }

    @Override
    protected void checkReadPermission() throws WebApiException {
        this.isHasRole(RoleType.COMMON_USER);
    }

    @Override
    protected void checkUpdatePermission() throws WebApiException {
        this.isHasRole(RoleType.COMMON_USER);
    }

    @Override
    protected void checkDeletePermission() throws WebApiException {
        this.isHasRole(RoleType.COMMON_USER);
    }
}
