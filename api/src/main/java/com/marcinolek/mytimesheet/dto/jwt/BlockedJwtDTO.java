package com.marcinolek.mytimesheet.dto.jwt;

import com.marcinolek.mytimesheet.dto.base.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class BlockedJwtDTO extends AbstractDTO {

    private String token;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endBlockDate;

}
