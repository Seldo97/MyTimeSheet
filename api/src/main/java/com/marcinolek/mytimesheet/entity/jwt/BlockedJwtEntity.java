package com.marcinolek.mytimesheet.entity.jwt;

import com.marcinolek.mytimesheet.entity.base.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Table(name = "blocked_jwt")
@Entity
@Getter
@Setter
public class BlockedJwtEntity extends AbstractEntity {

    private String token;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endBlockDate;
}
