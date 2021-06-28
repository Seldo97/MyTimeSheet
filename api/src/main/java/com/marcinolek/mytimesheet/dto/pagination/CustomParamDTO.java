package com.marcinolek.mytimesheet.dto.pagination;

import com.marcinolek.mytimesheet.constants.pagination.ParamObjectType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomParamDTO {
    String param;

    ParamObjectType type;

    Object value;

    public boolean isValid() {
        return this.value != "" && this.value != null;
    }
}
