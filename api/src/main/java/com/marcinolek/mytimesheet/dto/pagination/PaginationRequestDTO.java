package com.marcinolek.mytimesheet.dto.pagination;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
public class PaginationRequestDTO {

    private int page;

    private String orderPropertyName;

    private Sort.Direction sortDirection;

    private int itemsPerPage;

    private List<CustomParamDTO> params = new ArrayList<>();

    public CustomParamDTO getParam(String param) {
        Optional<CustomParamDTO> customParamOptional = this.params.stream().filter(x -> x.param.equals(param)).findFirst();
        return customParamOptional.orElse(null);
    }
}
