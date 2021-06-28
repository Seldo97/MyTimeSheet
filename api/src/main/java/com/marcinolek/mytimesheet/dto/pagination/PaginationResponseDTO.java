package com.marcinolek.mytimesheet.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponseDTO<TDto> {
    List<TDto> items;
    long totalCount;
    int totalPages;
    boolean isFirst;
    boolean isLast;

    public PaginationResponseDTO(Page<TDto> page) {
        this.items = page.getContent();
        this.totalCount = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.isFirst = page.isFirst();
        this.isLast = page.isLast();
    }
}
